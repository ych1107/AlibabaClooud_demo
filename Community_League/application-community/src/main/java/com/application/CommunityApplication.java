package com.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author 14501
 * EnableWebMvc 解决Swagger3与Spring高版本的适配问题
 */
@SpringBootApplication
@EnableWebMvc
public class CommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);

        // j api docs 生成方便但不准确且难更改
//        DocsConfig config = new DocsConfig();
//        // 项目根目录
//        config.setProjectPath("D:\\JavaProjects\\League management\\Community_League\\application-community");
//        // 项目名称
//        config.setProjectName("application-community");
//        // 声明该API的版本
//        config.setApiVersion("V1.0");
//        // 生成API 文档所在目录
//        config.setDocsPath("D:\\JavaProjects\\League management\\Community_League\\application-community\\apiDoc");
//        // 配置自动生成
//        config.setAutoGenerate(Boolean.TRUE);
//        // 执行生成文档
//        Docs.buildHtmlDocs(config);
    }
}
