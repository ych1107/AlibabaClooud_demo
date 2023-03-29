package com.example.controller;

import com.dftdlaRedis.cache.RedisCache;
import com.example.service.impl.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 14501
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    private static final String HELLO_WORLD ="Hello_2023_3_22";

    private static final String HELLO_TEST ="test";

    @Resource
    private RedisCache redisCache;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private testService testServ;

    /**
     * seata分布式测试
     * SpringSecurity+jwt权限测试
     * @param id
     */
    @GetMapping("/seata/{id}")
//    @PreAuthorize("hasAuthority('seata')")
    public void seata(@PathVariable("id") Long id){
        testServ.testSeata(id);
    }


    /**
     * SpringSecurity+jwt权限测试
     */
    @GetMapping
//    @PreAuthorize("hasAuthority('hello')")
    public String hello() {

//        redisCache.setCacheObject(HELLO_WORLD,((String)redisCache.getCacheObject(HELLO_WORLD)).concat("-"));
//        Object cacheTest = redisCache.getCacheObject(HELLO_TEST);
//        System.out.println(cacheTest);
//        if(cacheTest != null){
//            redisCache.setCacheObject(HELLO_TEST,Integer.parseInt((String.valueOf(cacheTest)))-1);
//            throw new RuntimeException("我故意的");
//        }else{
//            redisCache.setCacheObject(HELLO_TEST,10);
//        }
//
//        return redisCache.getCacheObject(HELLO_TEST);

//        redisCache.setCacheObject(HELLO_WORLD,"202013095ccx");

        redisTemplate.opsForValue().set(HELLO_WORLD, "202013095");

        return redisCache.getCacheObject(HELLO_WORLD);
    }

}
