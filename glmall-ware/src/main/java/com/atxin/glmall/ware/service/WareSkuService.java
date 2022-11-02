package com.atxin.glmall.ware.service;

import com.atxin.common.to.OrderTo;
import com.atxin.common.to.mq.StockLockedTo;
import com.atxin.glmall.ware.vo.SkuHasStockVo;
import com.atxin.glmall.ware.vo.WareSkuLockVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atxin.common.utils.PageUtils;
import com.atxin.glmall.ware.entity.WareSkuEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author xinjie
 * @email 2811252742@qq.com
 * @date 2022-08-09 09:37:26
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock( Long skuId,  Long wareId,  Integer skuNum);
    //检查商品是否有库存
    List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);

    boolean orderLockStock(WareSkuLockVo vo);

    void unlockStock(StockLockedTo to);

    @Transactional(rollbackFor = Exception.class)
    void unlockStock(OrderTo orderTo);

    @Transactional(rollbackFor = Exception.class)
    void unlockStock(OrderTo orderTo);
}

