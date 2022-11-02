package com.atxin.glmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atxin.common.utils.PageUtils;
import com.atxin.glmall.member.entity.MemberCollectSubjectEntity;

import java.util.Map;

/**
 * 会员收藏的专题活动
 *
 * @author xinjie
 * @email 2811252742@qq.com
 * @date 2022-08-09 08:50:48
 */
public interface MemberCollectSubjectService extends IService<MemberCollectSubjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

