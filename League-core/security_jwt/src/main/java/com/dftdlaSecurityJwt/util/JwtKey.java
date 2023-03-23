package com.dftdlaSecurityJwt.util;

public class JwtKey {

    /**
     * @return 登录状态key
     */
    public static String LOGIN_KEY(String s){
        return "login:"+s;
    }

}
