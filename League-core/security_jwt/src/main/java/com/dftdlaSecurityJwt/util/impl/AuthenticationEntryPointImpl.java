package com.dftdlaSecurityJwt.util.impl;

import com.alibaba.fastjson.JSON;
import com.dftdla.result.ResponseResult;
import com.dftdla.util.CommonUtils;
import com.dftdlaSecurityJwt.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 14501
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), CommonUtils.isEmptyString(authException.getMessage())
                ?"认证失败请重新登录"
                :authException.getMessage());
        String json = JSON.toJSONString(result);
        authException.printStackTrace();
        WebUtils.renderString(response,json);
    }
}
