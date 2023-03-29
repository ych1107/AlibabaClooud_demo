package com.dftdla.util;

public class RedisKeyTTL {

    /**
     * 单位是S
     * 登录账户的token有效时长
     * 当该账户操作时,若有效时长偏低,则刷新时长
     */
    public static final Long USER_LOGIN = 60 * 60L;

}
