package com.atxin.glmall.search.feign;

import com.atxin.common.utils.R;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient("glmall-commodit")
public interface ProductFeignService {

    @GetMapping("/commodit/attr/info/{attrId}")
    public R attrInfo(@PathVariable("attrId") Long attrId);

}
