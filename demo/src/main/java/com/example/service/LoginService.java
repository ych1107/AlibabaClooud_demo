package com.example.service;

import com.dftdla.pojo.User;
import com.dftdla.result.ResponseResult;

public interface LoginService {

    /**
     *  用户登录
     * @param user
     * @return
     */
    ResponseResult login(User user);

    /**
     * 从SecurityContextHolder获取用户id退出登录
     * @return
     */
    ResponseResult logout();

}
