package com.dftldaElastic;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author 14501
 */
@SpringBootApplication
public class ElasticSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchApplication.class, args);
    }
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.238.135:9200")
        ));
    }
}
