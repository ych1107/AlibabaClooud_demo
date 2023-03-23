package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.two;

public interface twoService extends IService<two> {
    /**
     * sum--
     * @param id
     */
    void downSum(Long id);
}
