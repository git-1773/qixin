package com.show.qixin.api.system.service.impl;

import com.show.qixin.api.common.domain.system.Menu;
import com.show.qixin.api.common.exception.ServiceException;
import com.show.qixin.api.common.utils.MenuTreeBuilder;
import com.show.qixin.api.system.converter.MenuConverter;
import com.show.qixin.api.system.mapper.MenuMapper;
import com.show.qixin.api.system.mapper.RoleMapper;
import com.show.qixin.api.system.mapper.RoleMenuMapper;
import com.show.qixin.api.system.service.MenuService;
import com.show.qixin.api.system.vo.MenuNodeVO;
import com.show.qixin.api.system.vo.MenuVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 加载菜单树（按钮和菜单）
     *
     * @return
     */
    @Override
    public List<MenuNodeVO> findMenuTree() {
        List<Menu> menus = menuMapper.selectAll();
        List<MenuNodeVO> menuNodeVOS = MenuConverter.converterToALLMenuNodeVO(menus);
        return MenuTreeBuilder.build(menuNodeVOS);
    }

    /**
     * 添加菜单
     *
     * @param menuVO
     */
    @Override
    public Menu add(MenuVO menuVO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVO,menu);
        menu.setCreateTime(new Date());
        menu.setModifiedTime(new Date());
        menu.setAvailable(menuVO.isDisabled()?0:1);
        menuMapper.insert(menu);
        return menu;
    }

    /**
     * 删除菜单
     * @param id
     */
    @Override
    public void delete(Long id) {
        Menu menu = menuMapper.selectByPrimaryKey(id);
        if(menu==null){
            throw new ServiceException("要删除的菜单不存在");
        }
        menuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 编辑菜单
     * @param id
     * @return
     */
    @Override
    public MenuVO edit(Long id) {
        Menu menu = menuMapper.selectByPrimaryKey(id);
        if(menu==null){
            throw new ServiceException("该编辑的菜单不存在");
        }
        return MenuConverter.converterToMenuVO(menu);
    }

    /**
     * 更新菜单
     * @param id
     * @param menuVO
     */
    @Override
    public void update(Long id, MenuVO menuVO) {
        Menu dbMenu = menuMapper.selectByPrimaryKey(id);
        if(dbMenu==null){
            throw new ServiceException("要更新的菜单不存在");
        }
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVO,menu);
        menu.setId(id);
        menu.setAvailable(menuVO.isDisabled()?0:1);
        menu.setModifiedTime(new Date());
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    /**
     * 获取展开项
     *
     * @return
     */
    @Override
    public List<Long> findOpenIds() {
        List<Long> ids=new ArrayList<>();
        List<Menu> menus = menuMapper.selectAll();
        if(!CollectionUtils.isEmpty(menus)){
            for (Menu menu : menus) {
                if(menu.getOpen()==1){
                    ids.add(menu.getId());
                }
            }
        }
        return ids;
    }



    /**
     * 获取所有菜单
     * @return
     */
    @Override
    public List<Menu> findAll() {
        return menuMapper.selectAll();
    }


}
