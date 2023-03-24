package com.hncj.controller;

import com.hncj.clients.HelloClient;
import com.hncj.mapper.UserMapper;
import com.hncj.pojo.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 14501
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HelloClient helloClient;

    /**
     * 获取Truth头，查询是否经过gateway
     * @param truth
     * @return
     */
    @GetMapping("/truth")
    private String hello(@RequestHeader("Truth") String truth) {
        System.out.println(truth);

        List<MyUser> users = userMapper.selectList(null);

        return users.toString();
    }

    @GetMapping("/client")
    public String helloFeign() {
        return helloClient.hello();
    }

}
