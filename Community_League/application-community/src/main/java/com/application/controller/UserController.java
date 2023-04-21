package com.application.controller;

import com.application.service.UserService;
import com.dftdla.pojo.User;
import com.dftdla.result.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户操作
 * @author 14501
 */
@Api(tags = "用户操作接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param user
     * @param file
     * @return
     */
    @PostMapping("/register")
    public ResponseResult register(User user, @RequestParam(value = "file",required = false) MultipartFile file){
        return userService.userRegister(user,file);
    }

    /**
     * 用户更新
     * @param user
     * @param file
     * @return
     */
    @PutMapping("/update")
    public ResponseResult updateUser(User user, @RequestParam(value = "file",required = false) MultipartFile file){
        return userService.userUpdate(user, file);
    }

}
