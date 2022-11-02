package com.atxin.glmall.commodit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@EnableCaching
@EnableFeignClients(basePackages = "com.atxin.glmall.commodit.feign")
@MapperScan("com.atxin.glmall.commodit.dao")
@SpringBootApplication
@EnableDiscoveryClient
//配置逻辑删除注解！！

/*3、JSR303 数据校验
Bean添加校验注解

 */
public class GlmallCommoditApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlmallCommoditApplication.class, args);
    }

}
