package com.dftdlaMp.util;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dftdla.util.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自定义元数据处理器
 * */
@Component
@Slf4j
public
class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //BaseContext.Id为null
        log.info("公共字段自动填充[insert]...");
        metaObject.setValue("createBy", BaseContext.getId());
        metaObject.setValue("updateBy",BaseContext.getId());
        metaObject.setValue("createTime",LocalDateTime.now());
        metaObject.setValue("updateTime",LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        log.info("公共字段自动填充[update]...");
        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("updateBy",BaseContext.getId());

    }
}
