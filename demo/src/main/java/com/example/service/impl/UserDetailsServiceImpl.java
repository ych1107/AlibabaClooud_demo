package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dftdla.pojo.User;
import com.dftdlaSecurityJwt.pojo.LoginUser;
import com.dftdlaSecurityJwt.util.MetaUserDetailsService;
import com.example.mapper.UserAuthenticeMapper;
import com.example.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/** security
 *  UserDetailServiceImpl 示例
 *  校验用户token
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements MetaUserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAuthenticeMapper userAuthenticationService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(wrapper);
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            log.info("没查到你啊,小伙子你怎么回事？");
            throw new RuntimeException("用户名或密码错误");
        }
        log.info(user.toString());
        //TODO 根据用户查询权限信息 添加到LoginUser中
        List<String> authenticationNames = userAuthenticationService.getAuthenticationNames(user.getId());


        if(authenticationNames.size() != 0) log.info(String.valueOf(authenticationNames));
        else log.error("无任何权限");
        //封装成UserDetails对象返回
        return new LoginUser(user,authenticationNames);
    }
}
