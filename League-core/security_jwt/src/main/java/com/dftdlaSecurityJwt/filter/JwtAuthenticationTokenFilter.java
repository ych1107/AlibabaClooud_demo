package com.dftdlaSecurityJwt.filter;

import com.alibaba.fastjson.JSON;
import com.dftdlaRedis.cache.RedisCache;
import com.dftdlaSecurityJwt.pojo.LoginUser;
import com.dftdlaSecurityJwt.util.JwtKey;
import com.dftdlaSecurityJwt.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.dftdla.util.JsonConverter.JsonToEvery;

/**
 * @author 14501
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Token非法");
        }
        //从redis中获取用户信息
        String redisKey = JwtKey.LOGIN_KEY(userid);
        LoginUser loginUser = JsonToEvery(redisCache.getCacheObject(redisKey),LoginUser.class);

        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }

        //存入SecurityContextHolder 三参数方式会设置 已认证参数
        //TODO 获取权限信息封装到Authentication中
//        throw new RuntimeException("运行到获取权限信息！");

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(request, response);
    }
}
