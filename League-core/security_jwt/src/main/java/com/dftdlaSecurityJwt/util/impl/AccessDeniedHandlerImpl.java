package com.dftdlaSecurityJwt.util.impl;

import com.alibaba.fastjson.JSON;
import com.dftdla.result.ResponseResult;
import com.dftdla.util.CommonUtils;
import com.dftdlaSecurityJwt.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 14501
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), CommonUtils.isEmptyString(accessDeniedException.getMessage())
                ?"权限不足"
                :accessDeniedException.getMessage());
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);
    }
}
