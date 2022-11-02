package com.atxin.glmall.ware.feign;

import com.atxin.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("glmall-commodit")
public interface CommoditFeignService {
    /**
     *      /commodit/skuinfo/info/{skuId}
     *
     *
     *   1)、让所有请求过网关；
     *          1、@FeignClient("gulimall-gateway")：给gulimall-gateway所在的机器发请求
     *          2、/api/commodit/skuinfo/info/{skuId}
     *   2）、直接让后台指定服务处理
     *          1、@FeignClient("gulimall-gateway")
     *          2、/commodit/skuinfo/info/{skuId}
     *
     * @return
     */
    @RequestMapping("/commodit/skuinfo/info/{skuId}")
    public R info(@PathVariable("skuId") Long skuId);
}
