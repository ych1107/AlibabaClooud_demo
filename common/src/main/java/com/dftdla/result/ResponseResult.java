package com.dftdla.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author 14501
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "统一返回类",description = "所有返回的数据集合")
public class ResponseResult<T> {
    /**
     * 状态码
     */
    @ApiModelProperty("状态码")
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    @ApiModelProperty("提示信息，如果有错误时，前端可以获取该字段进行提示")
    private String msg;
    /**
     * 查询到的结果数据
     */
    @ApiModelProperty("查询到的结果数据")
    private T data;
    /**
     * 当前时间戳
     */
    @ApiModelProperty("当前时间戳")
    private Long time;

    public ResponseResult(Integer code, String msg) {
        this.time = System.currentTimeMillis();
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data) {
        this.time = System.currentTimeMillis();
        this.code = code;
        this.data = data;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.time = System.currentTimeMillis();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
