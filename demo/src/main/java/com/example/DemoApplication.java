package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * /@EnableGlobalMethodSecurity (prePostEnabled = true) 开启SpringSecurity
 * @author 14501
 */
@SpringBootApplication
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
