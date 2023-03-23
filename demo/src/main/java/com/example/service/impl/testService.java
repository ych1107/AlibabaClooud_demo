package com.example.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class testService {

    @Autowired
    private oneServiceImpl oneService;

    @Autowired
    private twoServiceImpl twoService;

    @GlobalTransactional
    public void testSeata(Long id){
        oneService.downSum(id);
        twoService.downSum(id);
    }

}
