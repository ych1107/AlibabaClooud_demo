package com.application.controller;

import com.application.securityService.LoginService;
import com.dftdla.pojo.User;
import com.dftdla.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author 14501
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }

    /**
     * 登出
     * @return
     */
    @GetMapping("/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

    /**
     * 测试连接
     * @return
     */
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    /**
     * 获取目前在线的人数
     * @return
     */
    @GetMapping("/countPerson")
    public ResponseResult countUser() {
        Set loginUser = redisTemplate.keys("login:*");
        return new ResponseResult(HttpStatus.OK.value(),loginUser==null?0:loginUser.size());
    }

}
