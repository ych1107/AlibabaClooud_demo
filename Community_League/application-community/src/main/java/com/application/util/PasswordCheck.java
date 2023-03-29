package com.application.util;

import com.dftdla.pojo.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 14501
 */
public class PasswordCheck {

    public static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    /**
     * 对user的password字段进行加密处理
     * @param
     * @return User
     */
    public static User PasswordBrc(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return user;
    }

}
