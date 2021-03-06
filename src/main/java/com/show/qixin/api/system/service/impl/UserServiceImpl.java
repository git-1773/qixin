package com.show.qixin.api.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.show.qixin.api.common.bean.LoginUser;
import com.show.qixin.api.common.bean.ResponseBean;
import com.show.qixin.api.common.config.jwt.JWTToken;
import com.show.qixin.api.common.domain.system.*;
import com.show.qixin.api.common.exception.ErrorCodeEnum;
import com.show.qixin.api.common.exception.ServiceException;
import com.show.qixin.api.common.utils.JWTUtils;
import com.show.qixin.api.common.utils.MD5Utils;
import com.show.qixin.api.common.utils.MenuTreeBuilder;
import com.show.qixin.api.system.converter.MenuConverter;
import com.show.qixin.api.system.converter.UserConverter;
import com.show.qixin.api.system.enums.UserStatusEnum;
import com.show.qixin.api.system.enums.UserTypeEnum;
import com.show.qixin.api.system.mapper.*;
import com.show.qixin.api.system.service.UserService;
import com.show.qixin.api.system.vo.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询用户
     * @param name 用户名
     * @return
     */
    @Override
    public User findUserByName(String name) {
        User t = new User();
        t.setUsername(name);
        return userMapper.selectOne(t);
    }

    @Override
    public User findUserByPhoneNum(String phoneNum) {
        User t = new User();
        t.setPhoneNumber(phoneNum);
        return userMapper.selectOne(t);
    }

    /**
     * 查询用户角色
     * @param id 用户ID
     * @return
     */
    @Override
    public List<Role> findRolesById(Long id) {
        User dbUser = userMapper.selectByPrimaryKey(id);
        if(dbUser==null){
            throw new ServiceException("该用户不存在");
        }
        List<Role> roles=new ArrayList<>();
        UserRole t = new UserRole();
        t.setUserId(dbUser.getId());
        List<UserRole> userRoleList = userRoleMapper.select(t);
        List<Long> rids=new ArrayList<>();
        if(!CollectionUtils.isEmpty(userRoleList)){
            for (UserRole userRole : userRoleList) {
                rids.add(userRole.getRoleId());
            }
            if(!CollectionUtils.isEmpty(rids)){
                for (Long rid : rids) {
                    Role role = roleMapper.selectByPrimaryKey(rid);
                    if(role!=null){
                        roles.add(role);
                    }
                }
            }
        }
        return roles;
    }

    /**
     * 查询权限
     * @param roles 用户的角色
     * @return
     */
    @Override
    public List<Menu> findMenuByRoles(List<Role> roles) {
        List<Menu> menus=new ArrayList<>();
        if(!CollectionUtils.isEmpty(roles)){
            Set<Long> menuIds=new HashSet<>();//存放用户的菜单id
            List<RoleMenu> roleMenus;
            for (Role role : roles) {
                //根据角色ID查询权限ID
                Example o = new Example(RoleMenu.class);
                o.createCriteria().andEqualTo("roleId",role.getId());
                roleMenus= roleMenuMapper.selectByExample(o);
                if(!CollectionUtils.isEmpty(roleMenus)){
                    for (RoleMenu roleMenu : roleMenus) {
                        menuIds.add(roleMenu.getMenuId());
                    }
                }
            }
            if(!CollectionUtils.isEmpty(menuIds)){
                for (Long menuId : menuIds) {
                    //该用户所有的菜单
                    Menu menu = menuMapper.selectByPrimaryKey(menuId);
                    if(menu!=null){
                        menus.add(menu);
                    }
                }
            }
        }
        return menus;
    }

    /**
     * 获取菜单
     * @return
     */
    @Override
    public List<MenuNodeVO> findMenu() {
        List<Menu> menus=null;
        List<MenuNodeVO> menuNodeVOS=new ArrayList<>();
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if(loginUser.getUser().getType()== UserTypeEnum.SYSTEM_ADMIN.getTypeCode()){
            //超级管理员
            menus=menuMapper.selectAll();
        }else if(loginUser.getUser().getType()== UserTypeEnum.SYSTEM_USER.getTypeCode()){
            //普通系统用户
            menus= loginUser.getMenus();
        }
        if(!CollectionUtils.isEmpty(menus)){
            menuNodeVOS= MenuConverter.converterToMenuNodeVO(menus);
        }
        //构建树形菜单
        return MenuTreeBuilder.build(menuNodeVOS);
    }

    /**
     * 用户列表
     * @param userVO
     * @return
     */
    @Override
    public PageVO<UserVO> findUserList(Integer pageNum, Integer pageSize, UserVO userVO) {
        //已经拥有的
        PageHelper.startPage(pageNum,pageSize);
        Example o = new Example(User.class);
        String username = userVO.getUsername();
        String nickname = userVO.getNickname();
        Integer sex = userVO.getSex();
        String email = userVO.getEmail();
        Example.Criteria criteria = o.createCriteria();
        if(username!=null&&!"".equals(username)){
            criteria.andLike("username","%"+username+"%");
        }
        if(nickname!=null&&!"".equals(nickname)){
            criteria.andLike("nickname","%"+nickname+"%");
        }
        if(email!=null&&!"".equals(email)){
            criteria.andLike("email","%"+email+"%");
        }
        if(sex!=null){
            criteria.andEqualTo("sex",sex);
        }

        criteria.andNotEqualTo("type",0);
        List<User> userList = userMapper.selectByExample(o);
        List<UserVO> userVOS = userConverter.converterToUserVOList(userList);
        PageInfo<User> info=new PageInfo<>(userList);
        return new PageVO<>(info.getTotal(),userVOS);
    }

    /**
     * 删除用户
     * @param id 用户ID
     */
    @Transactional
    @Override
    public void deleteById(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        if(user==null){
            throw new ServiceException("要删除的用户不存在");
        }

        if(user.getId().equals(loginUser.getUser().getId())){
            throw new ServiceException("不能删除当前登入用户");
        }

        userMapper.deleteByPrimaryKey(id);
        //删除对应[用户-角色]记录
        Example o = new Example(UserRole.class);
        o.createCriteria().andEqualTo("userId",id);
        userRoleMapper.deleteByExample(o);
    }

    /**
     * 更新用户禁用状态
     * @param id
     * @param status
     */
    @Override
    public void updateStatus(Long id, Boolean status) {
        User dbUser = userMapper.selectByPrimaryKey(id);
        if(dbUser==null){
            throw new ServiceException("要更新状态的用户不存在");
        }
        LoginUser loginUser= (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if(dbUser.getId().equals(loginUser.getUser().getId())){
            throw new ServiceException(ErrorCodeEnum.DoNotAllowToDisableTheCurrentUser);
        }else {
            User t = new User();
            t.setId(id);
            t.setStatus(status? UserStatusEnum.DISABLE.getStatusCode() :
                    UserStatusEnum.AVAILABLE.getStatusCode());
            userMapper.updateByPrimaryKeySelective(t);
        }
    }

    /**
     * 用户信息注册@param userVO
     * @return
     */
    @Transactional
    @Override
    public ResponseBean register(UserVO userVO) {
        if(userVO == null){
            return ResponseBean.error("参数无效");
        }
        if(StringUtils.isEmpty(userVO.getUsername())){
            return ResponseBean.error("参数用户名无效");
        }
        if(StringUtils.isEmpty(userVO.getPassword())){
            return ResponseBean.error("参数密码无效");
        }
        if(StringUtils.isEmpty(userVO.getNickname())){
            return ResponseBean.error("参数真实姓名无效");
        }
        if(StringUtils.isEmpty(userVO.getPhoneNumber())){
            return ResponseBean.error("参数手机号无效");
        }
//        @NotBlank(message = "用户名不能为空") String username = userVO.getUsername();
//        @NotBlank(message = "密码不能为空") String password = userVO.getPassword();
        Example o = new Example(User.class);
        o.createCriteria().andEqualTo("username",userVO.getUsername());
        int i = userMapper.selectCountByExample(o);
        if(i!=0){
            return ResponseBean.error("该用户名[" + userVO.getUsername() + "]已被占用");
//            throw new ServiceException("该用户名[" + userVO.getUsername() + "]已被占用");
        }
        Example o1 = new Example(User.class);
        o.createCriteria().andEqualTo("phoneNumber",userVO.getPhoneNumber());
        int i1 = userMapper.selectCountByExample(o);
        if(i!=0){
            return ResponseBean.error("该手机号[" + userVO.getPhoneNumber() + "]已被注册");
//            throw new ServiceException("该用户名[" + userVO.getUsername() + "]已被占用");
        }
        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        String salt=UUID.randomUUID().toString().substring(0,32);
        user.setPassword(MD5Utils.md5Encryption(user.getPassword(), salt));
        user.setModifiedTime(new Date());
        user.setCreateTime(new Date());
        user.setSalt(salt);
        user.setType(UserTypeEnum.SYSTEM_USER.getTypeCode());//添加的用户默认是普通用户
        user.setStatus(UserStatusEnum.AVAILABLE.getStatusCode());//添加的用户默认启用
        user.setAvatar("http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg");
        userMapper.insert(user);
        return ResponseBean.success();
    }

    /**
     * 添加用户
     * @param userVO
     */
    @Transactional
    @Override
    public void add(UserVO userVO) {
        @NotBlank(message = "用户名不能为空") String username = userVO.getUsername();
        Example o = new Example(User.class);
        o.createCriteria().andEqualTo("username",username);
        int i = userMapper.selectCountByExample(o);
        if(i!=0){
            throw new ServiceException("该用户名已被占用");
        }
        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        String salt=UUID.randomUUID().toString().substring(0,32);
        user.setPassword(MD5Utils.md5Encryption(user.getPassword(), salt));
        user.setModifiedTime(new Date());
        user.setCreateTime(new Date());
        user.setSalt(salt);
        user.setType(UserTypeEnum.SYSTEM_USER.getTypeCode());//添加的用户默认是普通用户
        user.setStatus(UserStatusEnum.AVAILABLE.getStatusCode());//添加的用户默认启用
        user.setAvatar("http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg");
        userMapper.insert(user);
    }

    /**
     * 更新
     * @param id
     * @param userVO
     */
    @Transactional
    @Override
    public void update(Long id, UserEditVO userVO) {
        User dbUser = userMapper.selectByPrimaryKey(id);
        @NotBlank(message = "用户名不能为空") String username = userVO.getUsername();
        if(dbUser==null){
            throw new ServiceException("要删除的用户不存在");
        }
        Example o = new Example(User.class);
        o.createCriteria().andEqualTo("username",username);
        List<User> users = userMapper.selectByExample(o);
        if(!CollectionUtils.isEmpty(users)){
            if(!users.get(0).getId().equals(id)){
                throw new ServiceException("该用户名已被占用");
            }
        }
        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        user.setModifiedTime(new Date());
        user.setId(dbUser.getId());
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 编辑
     * @param id
     * @return
     */
    @Transactional
    @Override
    public UserEditVO edit(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        if(user==null){
            throw new ServiceException("要编辑的用户不存在");
        }
        UserEditVO userEditVO = new UserEditVO();
        BeanUtils.copyProperties(user,userEditVO);
        return userEditVO;
    }

    /**
     * 用户拥有的角色ID
     * @param id 用户id
     * @return
     */
    @Transactional
    @Override
    public List<Long> roles(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        if(user==null){
            throw new ServiceException("该用户不存在");
        }
        Example o = new Example(UserRole.class);
        o.createCriteria().andEqualTo("userId",user.getId());
        List<UserRole> userRoleList = userRoleMapper.selectByExample(o);
        List<Long> roleIds=new ArrayList<>();
        if(!CollectionUtils.isEmpty(userRoleList)){
            for (UserRole userRole : userRoleList) {
                Role role = roleMapper.selectByPrimaryKey(userRole.getRoleId());
                if(role!=null){
                    roleIds.add(role.getId());
                }
            }
        }
        return roleIds;
    }

    /**
     * 分配角色
     * @param id 用户id
     * @param rids 角色数组
     */
    @Override
    @Transactional
    public void assignRoles(Long id, Long[] rids) {
        //删除之前用户的所有角色
        User user = userMapper.selectByPrimaryKey(id);
        if(user==null){
            throw new ServiceException("用户不存在");
        }
        //删除之前分配的
        Example o = new Example(UserRole.class);
        o.createCriteria().andEqualTo("userId",user.getId());
        userRoleMapper.deleteByExample(o);
        //增加现在分配的
        if(rids.length>0){
            for (Long rid : rids) {
                Role role = roleMapper.selectByPrimaryKey(rid);
                if(role==null){
                    throw new ServiceException("roleId="+rid+",该角色不存在");
                }
                //判断角色状态
                if(role.getStatus()==0){
                    throw new ServiceException("roleName="+role.getRoleName()+",该角色已禁用");
                }
                UserRole userRole = new UserRole();
                userRole.setUserId(id);
                userRole.setRoleId(rid);
                userRoleMapper.insert(userRole);
            }
        }
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    /**
     * 用户登入
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) {
        String token;
        User user = findUserByName(username);
        if (user != null) {
            String salt = user.getSalt();
            //秘钥为盐
            String target = MD5Utils.md5Encryption(password, salt);
            //生成Token
            token = JWTUtils.sign(username, target);
            JWTToken jwtToken = new JWTToken(token);
            try {
                SecurityUtils.getSubject().login(jwtToken);
            } catch (AuthenticationException e) {
                throw new ServiceException(e.getMessage());
            }
        } else {
            throw new ServiceException(ErrorCodeEnum.USER_ACCOUNT_NOT_FOUND);
        }
        return token;
    }

    /**
     * 用户详情
     *
     * @return
     */
    @Override
    public UserInfoVO info() {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setAvatar(loginUser.getUser().getAvatar());
        userInfoVO.setUsername(loginUser.getUser().getUsername());
        userInfoVO.setUrl(loginUser.getUrls());
        userInfoVO.setNickname(loginUser.getUser().getNickname());
        List<String> roleNames = loginUser.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());
        userInfoVO.setRoles(roleNames);
        userInfoVO.setPerms(loginUser.getPermissions());
        userInfoVO.setIsAdmin(loginUser.getUser().getType()==UserTypeEnum.SYSTEM_ADMIN.getTypeCode());
        return userInfoVO;
    }

}
