package com.application.securityService;

import com.dftdla.pojo.User;
import com.dftdla.result.ResponseResult;

/**
 * 用户登录、登出服务
 * @author 14501
 */
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
