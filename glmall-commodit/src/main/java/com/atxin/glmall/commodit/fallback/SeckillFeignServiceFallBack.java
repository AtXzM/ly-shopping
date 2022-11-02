package com.atxin.glmall.commodit.fallback;


import com.atxin.common.exception.BizCodeEnum;
import com.atxin.common.utils.R;
import com.atxin.glmall.commodit.feign.SeckillFeignService;
import org.springframework.stereotype.Component;



@Component
public class SeckillFeignServiceFallBack implements SeckillFeignService {
    @Override
    public R getSkuSeckilInfo(Long skuId) {
        return R.error(BizCodeEnum.TO_MANY_REQUEST.getCode(),BizCodeEnum.TO_MANY_REQUEST.getMessage());
    }
}
