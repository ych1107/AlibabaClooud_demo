package com.application.service.Impl;

import com.application.controller.FileController;
import com.application.mapper.UserMapper;
import com.application.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dftdla.pojo.User;
import com.dftdla.result.ResponseResult;
import com.dftdla.util.BaseContext;
import com.dftdla.util.CommonUtils;
import com.dftdlaMinio.config.MinioConfig;
import com.dftdlaMinio.util.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import static com.application.util.PasswordCheck.PasswordBrc;

/**
 * @author 14501
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private MinioUtil minioUtil;
    @Resource
    private MinioConfig prop;
    @Resource
    private FileController fileController;

    @Override
    public ResponseResult userRegister(User user, MultipartFile file) {
        String url = null;

        try {

            if(file != null){
                String objectName = minioUtil.upload(file);
                if (null != objectName) {
                    url=prop.getEndpoint() + "/" + prop.getBucketName() + "/" + objectName;
                    log.info("用户的头像URL:{}",url);
                    user.setAvatar(url);
                }else {
                    return new ResponseResult(HttpStatus.SC_BAD_REQUEST,"头像上传出错！");
                }
            }
            //使用BCryptPasswordEncoder对user的password字段加密
            PasswordBrc(user);

            userMapper.insert(user);

            return new ResponseResult(HttpStatus.SC_OK,"注册成功!");
        } catch (Exception e) {
            e.printStackTrace();
            fileController.remove(url);
            String message = e.getMessage();
            if((!CommonUtils.isEmptyString(message))&&message.contains("Duplicate entry")) {
                return new ResponseResult(HttpStatus.SC_BAD_REQUEST,"用户名重复！");
            }
            return new ResponseResult(HttpStatus.SC_BAD_REQUEST,CommonUtils.isEmptyString(message)?"操作失败，请更换用户名或重试！": message);
        }
    }

    @Override
    public ResponseResult userUpdate(User user, MultipartFile file) {
    //修改不了自己 的信息

        if(!user.getId().equals(BaseContext.getId())){
            log.info(user+":"+BaseContext.getId());
            return new ResponseResult(HttpStatus.SC_BAD_REQUEST,"你无权修改别人的信息！");
        }

        String url = null;

        String avatar = user.getAvatar();

        try {

            if(file != null){
                String objectName = minioUtil.upload(file);
                if (null != objectName) {
                    url=prop.getEndpoint() + "/" + prop.getBucketName() + "/" + objectName;
                    if(!CommonUtils.isEmptyString(avatar)){
                        fileController.remove(avatar);
                    }
                    user.setAvatar(url);
                }else {
                    return new ResponseResult(HttpStatus.SC_BAD_REQUEST,"头像上传出错！");
                }
            }
            //使用BCryptPasswordEncoder对user的password字段加密
            PasswordBrc(user);

            userMapper.updateById(user);

            return new ResponseResult(HttpStatus.SC_OK,"更新成功!");
        } catch (Exception e) {
            e.printStackTrace();
            fileController.remove(url);
            String message = e.getMessage();
            if((!CommonUtils.isEmptyString(message))&&message.contains("Duplicate entry")) {
                return new ResponseResult(HttpStatus.SC_BAD_REQUEST,"用户名重复！");
            }
            return new ResponseResult(HttpStatus.SC_BAD_REQUEST,CommonUtils.isEmptyString(message)?"操作失败，请更换用户名或重试！": message);
        }

    }
}
