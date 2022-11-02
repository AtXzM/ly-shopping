package com.atguigu.glmall.seckill.feign;

import com.atxin.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@FeignClient("glmall-commodit")
public interface CommoditFeignService {

    @RequestMapping("/commodit/skuinfo/info/{skuId}")
    R getSkuInfo(@PathVariable("skuId") Long skuId);

}
