package com.dftdla.exception;

import com.dftdla.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author 14501
 */
@Slf4j
@ResponseBody
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalException {

    /**
     *  异常处理
     *  全局的sql添加冲突
     *  @return
     * */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseResult exceptionHandler(SQLIntegrityConstraintViolationException ex){

        log.error(ex.getMessage());

        if(ex.getMessage().contains("Duplicate entry")){
            String t[]=ex.getMessage().split(" ");
            return new ResponseResult(HttpStatus.BAD_REQUEST.value(),t[2]+"已存在");
        }

        return new ResponseResult(HttpStatus.BAD_REQUEST.value(),"未知的SQL错误");

    }


    /**
     * 全局未知异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult globalException(Exception e){
        e.printStackTrace();
        return new ResponseResult(HttpStatus.BAD_REQUEST.value(),"未知的服务器错误！");
    }
}
