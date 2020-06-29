package com.show.qixin.api.system.controller;

import com.show.qixin.api.common.bean.ResponseBean;
import com.show.qixin.api.system.service.LoginLogService;
import com.show.qixin.api.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

@Slf4j
@RestController
@RequestMapping("/auth")
@Validated
@Api(value = "系统登录模块", tags = "系统登录模块接口")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginLogService loginLogService;

    /**
     * 用户登入
     *
     * @param username: 用户名
     * @param password: 密码
     * @return
     */
    @ApiOperation(value = "用户登入", notes = "接收参数用户名和密码,登入成功后,返回JWTToken")
    @PostMapping("/login")
    public ResponseBean login(@NotBlank(message = "账号必填") String username,
                              @NotBlank(message = "密码必填") String password,
                              HttpServletRequest request) {
        String token= "";
        try {
            token = userService.login(username,password);
            loginLogService.add(request);
        } catch (Exception e) {
            log.error("登录失败==> " + e.getMessage(), e);
            e.printStackTrace();
            return ResponseBean.error(e.getMessage());
        }
        return ResponseBean.success((Object) token);
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public ResponseBean logout(){
        SecurityUtils.getSubject().logout();
        return ResponseBean.success("退出成功");
    }

}
