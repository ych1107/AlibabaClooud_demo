package com.application.service.Impl;

import com.application.mapper.UserActivityMapper;
import com.application.pojo.UserActivity;
import com.application.service.UserActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author 14501
* @description 针对表【user_activity】的数据库操作Service实现
* @createDate 2023-03-26 11:58:45
*/
@Service
public class UserActivityServiceImpl extends ServiceImpl<UserActivityMapper, UserActivity>
    implements UserActivityService {

}




