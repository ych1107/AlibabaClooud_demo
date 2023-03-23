package com.example;

import com.dftdlaRedis.cache.RedisCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author 14501
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
    }

    @Bean(value = "redisCache")
    public RedisCache redisCache(){
        return new RedisCache();
    }

}
