package com.dftdla.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页数据
 * @author 14501
 */
@Data
@NoArgsConstructor
public class QueryPage<T> {

    /**
     * 页码
     */
    private Long pageNumber;
    /**
     * 页大小
     */
    private Long pageSize;
    /**
     * 查询到的数据
     */
    private T data;
    /**
     * 查询条件
     */
    private T condition;

}
