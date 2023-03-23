package com.example;

import com.dftdlaRedis.cache.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class testLoad {

    @Autowired
    private RedisCache redisCache;

    @Test
    void contextLoad() {
        redisCache.setCacheObject("TestCCX","ccx");
    }

}
