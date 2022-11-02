package com.atxin.glmall.commodit.feign;


import com.atxin.common.to.es.SkuEsModel;
import com.atxin.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;



@FeignClient("glmall-search")
public interface SearchFeignService {

    @PostMapping(value = "/search/save/commodit")
     R commoditStatusUp(@RequestBody List<SkuEsModel> skuEsModels);

}
