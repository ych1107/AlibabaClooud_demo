# CloudAlibaba_concordance使用文档

​	本项目使用SpringBoot 2.7.5

​	部分配置采用了b站：黑马程序员、三更草堂



​	使用方式可参考demo、example、gateway



​	已配置：

- ElasticSearch
- Load-balancer
- MybatisPlus
- Nacos
- OpenFeign
- Redis
- Seata
- Sentinel
- SpringSecurity+Jwt



## League_management

​	父项目，用于管理服务所需要的父依赖，同时执行maven指令

​	项目配置主要依赖Nacos，请将other/nacosConfigs目录下的配置文件导入到Nacos

​	sql导入：



```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.7.5</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>

<groupId>com.hncj</groupId>
<artifactId>League-management</artifactId>
<version>1.0-SNAPSHOT</version>
<packaging>pom</packaging>

<modules>
    <module>League-core</module>
    <module>example</module>
    <module>common</module>
    <module>demo</module>
    <module>gateway</module>
    <module>rabbit-consumer</module>
    <module>rabbit-publisher</module>
</modules>

<dependencyManagement>
    <dependencies>
        <!-- Nacos 父工程依赖-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-dependencies</artifactId>
            <version>2021.0.4.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <!--mq父工程依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
        </dependency>
    </dependencies>
</dependencyManagement>

<dependencies>
    <!--项目热部署-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
</dependencies>
```



## logs

​	子项目的日志存放目录，便于查看bug调试



## common



### pom

​	子项目的通用pom配置，导入各种实用坐标，目前含有：

- spring-boot-starter-web 2.7.5(boot官方)
- spring-boot-starter-test 2.7.5(boot官方)
- spring-boot-starter-log4j 1.3.8.RELEASE(boot官方)
- lombok (org.projectlombok) 1.18.24
- fastjson (阿里官方) 1.2.78
- commons-pool2 (阿帕奇官方) 2.11.1
- commons-lang3 (阿帕奇官方) 3.12.0



### pojo

​	设置通用实体类，标注公共字段

#### User

```java
package com.dftdla.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户表(User)实体类
 *
 * @author 14501
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * 账号状态（0正常 1停用）
     */
    private String status;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phonenumber;
    /**
     * 用户性别（0男，1女，2未知）
     */
    private String sex;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户类型（0管理员，1普通用户）
     */
    private String userType;
    /**
     * 创建人的用户id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;
}
```



### result

​	通用返回值类型

```java
/**
 *
 * @author 14501
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
```



### util

​	通用工具类

#### JsonConverter

```java
/**
 * JSON转换器 当FastJson报错使用
 */
public class JsonConverter {

    public static  <T> T JsonToEvery(T t,Class<T> cl){
        return JSON.parseObject(JSON.toJSONString(t), cl);
    }

}
```

#### StatusCode

​	自定义的响应码

```java
public class StatusCode {
    /**
     * 响应成功
     */
    public static final Integer RESPONSE_SUCCESS= 200;

    /**
     * 资源不存在
     */
    public static final Integer RESPONSE_NOT_FOUND= 404;

    /**
     * 未授权 或 未登录
     */
    public static final Integer RESPONSE_FORBIDDEN= 403;

    /**
     * 未知错误
     */
    public static final Integer RESPONSE_OTHER= 405;

}
```

## demo

​	League-Core使用示例，包括：

- Nacos子项目连接
- OpenFeign被调用服务
- MybatisPlus操作MySQL
- Seata分布式事务
- SpringSecurity+Jwt认证校验权限
- Redis缓存操作



## example

​	League-Core使用示例，包括：

- Sentinel持久化连接，熔断，限流测试
- OpenFeign服务调用
- MybatisPlus连接MySQL



## gateway

​	网关服务，可按照需要自行配置，包括：

- Sentinel(League-core)
- GateWay(cloud官方) 3.1.1
- Load-balancer(League-core)
- Nacos(League-core)

​	

​	建议连接Nacos配置中心，进行配置热更新



## rabbitmq

​	建议当做中转站，其余服务调用即可



## League-core

​	微服务依赖管理，配置微服务的共享依赖，控制执行maven指令

​	微服务包中的yml只做配置示例，无法生效

​	boot自动加载情况，请查看各包下的resources/META-INF/spring.factories

### ElasticSearch

- 在com.dftdlaElastic.config.elasticConfig中配置RestHighLevelClient
- 导入此包，使用自动装配即可获取RestHighLevelClient使用



### Load-balancer

​	当服务与nacos负载均衡出问题时，导入此包



### Mybatis-plus

​	在com.dftdlaMp.config.MybatisPlusConfig处配置MybatisPlus

​	在com.dftdlaMp.util.MyMetaObjectHandler处配置自定义元数据处理器

​	使用方式与平常一致，但配置在此包中配置



### Nacos

​	配置了nacos客户端的服务连接，配置连接，不需要配置连接时，在yml中配置一下即可：

```yml
spring:
  cloud:
    nacos:
      #2021.0.5版本引入nacos配置文件方式，无需bootstrap包 
      config:
        #关闭spring.config.import: nacos: XXXX.yml
        import-check:
          enabled: false
```

​	使用时导入



### OpenFeign

​	在com.dftdlaFeign.config.DefaultFeignConfig处配置OpenFeign

​	使用方式与平常一样，配置在此包中配置



### Redis

​	在com.dftdlaRedis.config配置redisTemplate

​	在com.dftdlaRedis.cache配置redis的api

​	导入此包即可直接使用redisCache或redisTemplate（泛型）



### Seata

​	在导入主目录nacosConfigs的配置文件以及sql的情况下，导入此包即可直接使用

​	注意：seata自身目录的配置需如下：

```json
registry {
  # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
  type = "nacos"

  nacos {
    application = "seata-tc-server"
    serverAddr = "127.0.0.1:8848"
    group = "DEFAULT_GROUP"
    namespace = ""
    cluster = "yc"
    username = "nacos"
    password = "nacos"
  }
}

config {
  # file、nacos 、apollo、zk、consul、etcd3
  type = "nacos"

  nacos {
    serverAddr = "127.0.0.1:8848"
    namespace = ""
    group = "SEATA_GROUP"
    username = "nacos"
    password = "nacos"
    dataId = "seataServer.properties"
  }
}
```

​	使用方式与平常一样



### Sentinel

​	导入此包即可使用



### SpringSecurity+Jwt

​	包含：SecurityConfig、JwtAuthenticationTokenFilter、UserAuthenticationMapper、LoginUser、自定义异常处理（认证、授权）、JwtUtil、UserDetailsService、JwtKey

​	详细请见各类中的注释

​	使用与平常一样，配置在此包中配置

