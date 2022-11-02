package com.atxin.glmall.commodit;

import com.atxin.glmall.commodit.dao.entity.BrandEntity;
import com.atxin.glmall.commodit.service.BrandService;
import com.atxin.glmall.commodit.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class GlmallCommoditApplicationTests {
@Autowired
    BrandService brandService;
@Autowired
    RedissonClient redissonClient;
    @Test
   public void contextLoads() {

        BrandEntity brandEntity = new BrandEntity();
         brandEntity.setDescript("安卓");
         brandService.save(brandEntity);
         System.out.println("保存成功。。。。");

    }
    @Autowired
    CategoryService categoryService;

    @Test
    public void testFindPath(){
        Long[] catelogPath = categoryService.findCatelogPath(225L);
        log.info("完整路径：{}", Arrays.asList(catelogPath));
    }
    @Test
    public  void redisson(){
        System.out.println(redissonClient);
    }
}
