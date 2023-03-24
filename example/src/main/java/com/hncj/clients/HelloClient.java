package com.hncj.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 14501
 */
@FeignClient("hello")
public interface HelloClient {

    /**
     * 远程调用demo的/hello接口
     * @return
     */
    @GetMapping("/hello")
    String hello();

}
