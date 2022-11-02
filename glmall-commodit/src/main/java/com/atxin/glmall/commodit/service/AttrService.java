package com.atxin.glmall.commodit.service;

import com.atxin.glmall.commodit.vo.AttrGroupRelationVo;
import com.atxin.glmall.commodit.vo.AttrRespVo;
import com.atxin.glmall.commodit.vo.AttrVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atxin.common.utils.PageUtils;
import com.atxin.glmall.commodit.dao.entity.AttrEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author xinjie
 * @email 2811252742@qq.com
 * @date 2022-08-07 18:56:52
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId,String type);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attr);

    void saveAttr(AttrVo attr);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    void deleteRelation(AttrGroupRelationVo[] vos);

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId);
   //在指定的所有属性中找到可以被检索的
    List<Long> selectSearchAttrs(List<Long> attrIds);
}

