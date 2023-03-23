package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.twoMapper;
import com.example.pojo.two;
import com.example.service.twoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class twoServiceImpl extends ServiceImpl<twoMapper, two> implements twoService {

    @Autowired
    private twoMapper map;

    @Override
    public void downSum(Long id) {
        two two = map.selectById(id);
        two.setSum(two.getSum()-1);
        map.updateById(two);
    }
}
