package com.atxin.glmall.commodit.service;

import com.atxin.glmall.commodit.vo.Catelog2Vo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atxin.common.utils.PageUtils;
import com.atxin.glmall.commodit.dao.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author xinjie
 * @email 2811252742@qq.com
 * @date 2022-08-07 18:56:52
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    void removeMenuByIds(List<Long> asList);
    //找到catelogId完整路径[父/子/孙]
    Long[] findCatelogPath(Long catelogId);



    void updateCascade(CategoryEntity category);

    List<CategoryEntity> getLevel1Categorys();

    Map<String, List<Catelog2Vo>> getCatalogJson();
}

