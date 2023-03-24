package com.example;

import com.dftdlaRedis.cache.RedisCache;
import com.example.mapper.CommunityMapper;
import com.example.pojo.Community;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class testLoad {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private CommunityMapper communityMapper;

    @Test
    void contextLoad() {
        Community community = new Community(1L,"test4","测试用的");
        communityMapper.insert(community);
    }

}
