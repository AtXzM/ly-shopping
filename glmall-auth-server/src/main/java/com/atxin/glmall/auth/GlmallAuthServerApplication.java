package com.atxin.glmall.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages ="com.atxin.glmall.auth.feign")
public class GlmallAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlmallAuthServerApplication.class, args);
    }

}
