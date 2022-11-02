package com.atxin.glmall.commodit.dao;

import com.atxin.glmall.commodit.dao.entity.AttrGroupEntity;
import com.atxin.glmall.commodit.vo.SpuItemAttrGroupVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性分组
 * 
 * @author xinjie
 * @email 2811252742@qq.com
 * @date 2022-08-07 18:56:52
 */
@Mapper
public interface AttrGroupDao extends BaseMapper<AttrGroupEntity> {

    List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(@Param("spuId") Long spuId, @Param("catalogId") Long catalogId);
}
