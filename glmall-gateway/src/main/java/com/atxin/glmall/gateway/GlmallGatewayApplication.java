package com.atxin.glmall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/*
开启网关
配置nacos注册中心地址
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class GlmallGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlmallGatewayApplication.class, args);
    }

}
