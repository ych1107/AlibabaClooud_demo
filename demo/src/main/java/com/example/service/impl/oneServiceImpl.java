package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.oneMapper;
import com.example.pojo.one;
import com.example.pojo.two;
import com.example.service.oneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class oneServiceImpl extends ServiceImpl<oneMapper, one> implements oneService {

    @Autowired
    public oneMapper map;

    public void downSum(Long id) {
        one one = map.selectById(id);
        one.setSum(one.getSum()-1);
        map.updateById(one);
    }

}
