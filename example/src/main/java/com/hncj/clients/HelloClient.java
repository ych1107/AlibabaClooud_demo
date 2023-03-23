package com.hncj.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 14501
 */
@FeignClient("hello")
public interface HelloClient {

    @GetMapping("/hello")
    String hello();

}
