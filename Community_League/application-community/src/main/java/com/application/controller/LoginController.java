package com.application.controller;

import com.application.securityService.LoginService;
import com.dftdla.pojo.User;
import com.dftdla.result.ResponseResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author 14501
 */
@Api(tags = "登录及测试连接接口")
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
    @ApiOperation(value = "本地用户登录",notes = "站内注册用户登录方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "登陆成功并返回用户信息！"),
            @ApiResponse(code = 400, message = "用户名或密码错误！")
    })
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }

    /**
     * 登出
     * @return
     */
    @ApiOperation(value = "登出",notes = "此账户登出")
    @ApiImplicitParam(paramType = "header", name = "token", required = true)
    @ApiResponses({
            @ApiResponse(code = 200, message = "登出成功！")
    })
    @GetMapping("/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

    /**
     * 测试连接
     * @return
     */
    @ApiOperation(value = "测试连接",notes = "测试连接")
    @ApiResponses({
            @ApiResponse(code = 200, message = "连接通畅！")
    })
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    /**
     * 获取目前在线的人数
     * @return
     */
    @ApiOperation(value = "获取目前在线的人数",notes = "通过redis键的个数判断目前在线人数")
    @ApiImplicitParam(paramType = "header", name = "token", required = true)
    @ApiResponses({
            @ApiResponse(code = 200, message = "获取成功")
    })
    @GetMapping("/countPerson")
    public ResponseResult countUser() {
        Set loginUser = redisTemplate.keys("login:*");
        return new ResponseResult(HttpStatus.OK.value(),loginUser==null?0:loginUser.size());
    }

}
