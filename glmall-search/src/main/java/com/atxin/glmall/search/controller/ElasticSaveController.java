package com.atxin.glmall.search.controller;


import com.atxin.common.exception.BizCodeEnum;
import com.atxin.common.to.es.SkuEsModel;
import com.atxin.common.utils.R;
import com.atxin.glmall.search.service.CommoditSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;




@Slf4j
@RequestMapping(value = "/search/save")
@RestController
public class ElasticSaveController {

    @Autowired
    private CommoditSaveService commoditSaveService;


    /**
     * 上架商品
     * @param skuEsModels
     * @return
     */
    @PostMapping(value = "/commodit")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels) {

        boolean b=false;
        try {
            b = commoditSaveService.commoditStatusUp(skuEsModels);
        } catch (IOException e) {
            log.error("商品上架错误{}",e);

            return R.error(BizCodeEnum.COMMODIT_UP_EXCEPTION.getCode(),BizCodeEnum.COMMODIT_UP_EXCEPTION.getMessage());
        }

        if(b){
            return R.error(BizCodeEnum.COMMODIT_UP_EXCEPTION.getCode(),BizCodeEnum.COMMODIT_UP_EXCEPTION.getMessage());


        }else {
            return R.ok();

        }

    }


}
