package com.hncj;

import com.hncj.clients.HelloClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 14501
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(clients = HelloClient.class)
public class TestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TestApplication.class, args);
        System.out.println(run.getEnvironment().getProperty("spring.application.name"));
    }

}
