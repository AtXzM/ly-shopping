package com.atxin.glmall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GlmallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlmallCouponApplication.class, args);
    }

}
