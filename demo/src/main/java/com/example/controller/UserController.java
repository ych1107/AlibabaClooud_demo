package com.example.controller;

import com.dftdla.pojo.User;
import com.dftdla.result.ResponseResult;
import com.example.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        log.info(user.toString());
        return loginService.login(user);
    }

    @GetMapping("/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

}
