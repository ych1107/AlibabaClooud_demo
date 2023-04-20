package com.application.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.application.securityService.LoginService;
import com.application.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dftdla.pojo.User;
import com.dftdla.result.ResponseResult;
import com.xkcoding.justauth.AuthRequestFactory;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 第三方登录 Controller
 * </p>
 * @author 14501
 * @description: 第三方登录 Controller
 */
@Slf4j
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Autowired
    private AuthRequestFactory factory;

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    /**
     * 登录类型
     */
    @GetMapping
    public Map<String, String> loginType() {
        List<String> oauthList = factory.oauthList();
        return oauthList.stream().collect(Collectors.toMap(oauth -> oauth.toLowerCase() + "登录", oauth -> "http://127.0.0.1:10010/oauth/login/" + oauth.toLowerCase()));
    }

    /**
     * 登录
     *
     * @param oauthType 第三方登录类型
     * @param response  response
     * @throws IOException
     */
    @RequestMapping("/login/{oauthType}")
    public void renderAuth(@PathVariable String oauthType, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = factory.get(getAuthSource(oauthType));
        response.sendRedirect(authRequest.authorize(oauthType + "::" + AuthStateUtils.createState()));
    }

    /**
     * 登录成功后的回调
     *
     * @param oauthType 第三方登录类型
     * @param callback  携带返回的信息
     * @return 登录成功后的信息
     */
    @GlobalTransactional
    @RequestMapping("/callback/{oauthType}")
    public ResponseResult login(@PathVariable String oauthType, AuthCallback callback) throws InterruptedException {

        String defaultPassword = "61056399d7307a22c632ed91e5b4";

        AuthRequest authRequest = factory.get(getAuthSource(oauthType));

        AuthResponse response = authRequest.login(callback);
        log.info("[response】= {}", JSONUtil.toJsonStr(response));

        AuthUser authUser = JSON.to(AuthUser.class, response.getData());

        User user = new User();
        user.setUserName(authUser.getSource()+authUser.getUuid());

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,user.getUserName());
        User getUser = userService.getOne(wrapper);

        if(Objects.isNull(getUser)){

            user.setAvatar(authUser.getAvatar());
            user.setNickName(authUser.getNickname()==null
                    ?authUser.getUsername()+authUser.getUuid()
                    :authUser.getNickname());
            user.setPassword(defaultPassword);

            userService.userRegister(user,null);

        }

        user.setPassword(defaultPassword);

        log.info("User:{}",user);

        return loginService.login(user);
    }

    private AuthSource getAuthSource(String type) {
        if (StrUtil.isNotBlank(type)) {
            return AuthSource.valueOf(type.toUpperCase());
        } else {
            throw new RuntimeException("不支持的类型");
        }
    }
}
