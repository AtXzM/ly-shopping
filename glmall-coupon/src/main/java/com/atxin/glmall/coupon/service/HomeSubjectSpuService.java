package com.atxin.glmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atxin.common.utils.PageUtils;
import com.atxin.glmall.coupon.entity.HomeSubjectSpuEntity;

import java.util.Map;

/**
 * δΈι’εε
 *
 * @author xinjie
 * @email 2811252742@qq.com
 * @date 2022-08-09 08:41:18
 */
public interface HomeSubjectSpuService extends IService<HomeSubjectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

