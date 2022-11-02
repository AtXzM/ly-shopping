package com.atxin.glmall.commodit.service;

import com.atxin.glmall.commodit.vo.SpuSaveVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atxin.common.utils.PageUtils;
import com.atxin.glmall.commodit.dao.entity.SpuInfoEntity;

import java.util.Map;

/**
 * spu信息
 *
 * @author xinjie
 * @email 2811252742@qq.com
 * @date 2022-08-07 18:56:52
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVo vo);

    void saveBaseSpuInfo(SpuInfoEntity infoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);
  //商品上架
    void up(Long spuId);

    SpuInfoEntity getSpuInfoBySkuId(Long skuId);
}

