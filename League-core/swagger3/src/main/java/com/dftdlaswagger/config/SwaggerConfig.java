package com.dftdlaswagger.config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @author 14501
 * 在启动类加入@EnableWebMvc即可使用Swagger3
 * 此类只作配置类实例，可直接复制使用（把注解的注释解开即可）
 */
//@Configuration
//@EnableOpenApi
public class SwaggerConfig {

//    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title("社联帮接口文档")
                // 文档基本描述
                .description("application模块的接口说明")
                // 联系人信息
                .contact(new Contact("陈创新", "https://blog.csdn.net/qq_54230484", "axinfree@qq.com"))
                // 组织链接
                .termsOfServiceUrl("https://gitee.com/dftdla/share-community")
                // 版本
                .version("1.0")
                // 许可
                .license("Apache 2.0 许可")
                // 许可链接
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                // 拓展
                .extensions(new ArrayList<>())
                .build();
    }
}
