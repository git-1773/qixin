package com.show.qixin.api.system.controller;

import com.show.qixin.api.common.domain.system.User;
import com.show.qixin.api.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class CheckController {

    @Autowired
    private UserService userService;

    @PostMapping("/username")
    public boolean checkUserName(@RequestBody @Validated String username){
        User user = userService.findUserByName(username);
        return user == null ? true : false;
    }

    @PostMapping("/phoneNum")
    public boolean checkPhoneNum(@RequestBody @Validated String phoneNum){
        User user = userService.findUserByPhoneNum(phoneNum);
        return user == null ? true : false;
    }

}
