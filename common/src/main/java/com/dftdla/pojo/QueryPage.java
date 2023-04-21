package com.dftdla.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页数据
 * @author 14501
 */
@Data
@ApiModel(value = "分页信息类",description = "保存的分页信息")
@NoArgsConstructor
public class QueryPage<T> {

    /**
     * 页码
     */
    @ApiModelProperty("页码")
    private Long pageNumber;
    /**
     * 页大小
     */
    @ApiModelProperty("页大小")
    private Long pageSize;
    /**
     * 查询到的数据
     */
    @ApiModelProperty("查询到的数据")
    private T data;
    /**
     * 查询条件
     */
    @ApiModelProperty("查询条件")
    private T condition;

}
