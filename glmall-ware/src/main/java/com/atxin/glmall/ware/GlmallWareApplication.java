package com.atxin.glmall.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableRabbit
@EnableFeignClients
@EnableTransactionManagement
@MapperScan("com.atxin.glmall.ware.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class GlmallWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlmallWareApplication.class, args);
    }

}
