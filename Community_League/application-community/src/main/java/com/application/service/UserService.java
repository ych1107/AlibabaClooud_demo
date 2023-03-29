package com.application.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dftdla.pojo.User;
import com.dftdla.result.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户服务
 * 注册、
 * @author 14501
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param user
     * @param file
     * @return
     */
    ResponseResult userRegister(User user, MultipartFile file);

    /**
     * 用户更新
     * @param user
     * @param file
     * @return
     */
    ResponseResult userUpdate(User user, MultipartFile file);
}
