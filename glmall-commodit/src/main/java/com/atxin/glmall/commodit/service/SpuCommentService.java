package com.atxin.glmall.commodit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atxin.common.utils.PageUtils;
import com.atxin.glmall.commodit.dao.entity.SpuCommentEntity;

import java.util.Map;

/**
 * 商品评价
 *
 * @author xinjie
 * @email 2811252742@qq.com
 * @date 2022-08-07 18:56:52
 */
public interface SpuCommentService extends IService<SpuCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

