package com.atxin.glmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atxin.common.utils.PageUtils;
import com.atxin.glmall.order.entity.OrderReturnReasonEntity;

import java.util.Map;

/**
 * ้่ดงๅๅ 
 *
 * @author xinjie
 * @email 2811252742@qq.com
 * @date 2022-08-09 09:28:35
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

