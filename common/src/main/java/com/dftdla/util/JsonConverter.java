package com.dftdla.util;

import com.alibaba.fastjson.JSON;

/**
 * JSON转换器 当FastJson报错使用
 */
public class JsonConverter {

    public static  <T> T JsonToEvery(T t,Class<T> cl){
        return JSON.parseObject(JSON.toJSONString(t), cl);
    }

}
