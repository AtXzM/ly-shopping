package com.atxin.glmall.commodit.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.atxin.glmall.commodit.dao.entity.AttrEntity;
import com.atxin.glmall.commodit.service.AttrAttrgroupRelationService;
import com.atxin.glmall.commodit.service.AttrService;
import com.atxin.glmall.commodit.service.CategoryService;
import com.atxin.glmall.commodit.vo.AttrGroupRelationVo;
import com.atxin.glmall.commodit.vo.AttrGroupWithAttrsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atxin.glmall.commodit.dao.entity.AttrGroupEntity;
import com.atxin.glmall.commodit.service.AttrGroupService;
import com.atxin.common.utils.PageUtils;
import com.atxin.common.utils.R;



/**
 * 属性分组
 *
 * @author xinjie
 * @email 2811252742@qq.com
 * @date 2022-08-07 19:28:47
 */
@RestController
@RequestMapping("commodit/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    AttrService attrService;
    @Autowired
    AttrAttrgroupRelationService relationService;

    ///product/attrgroup/{catelogId}/withattr
    @GetMapping("/{catelogId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable("catelogId")Long catelogId){

        //1、查出当前分类下的所有属性分组，
        //2、查出每个属性分组的所有属性
        List<AttrGroupWithAttrsVo> vos =  attrGroupService.getAttrGroupWithAttrsByCatelogId(catelogId);
        return R.ok().put("data",vos);
    }

    //将属性分组进行关联
    @GetMapping("/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId){
        List<AttrEntity> entities =attrService.getRelationAttr(attrgroupId);
        return R.ok().put("data",entities);
    }
//新建关联使用 查询当前分组没有关联的属性
    @GetMapping("/{attrgroupId}/noattr/relation")
    public R attrNoRelation(@PathVariable("attrgroupId") Long attrgroupId,
                            @RequestParam Map<String,Object> params){
      PageUtils page=attrService.getNoRelationAttr(params,attrgroupId);
        return R.ok().put("page",page);
    }
    //新增关联（点击）
    @PostMapping("/attr/relation")
    public R addRelation(@RequestBody List< AttrGroupRelationVo> vos){
        relationService.saveBatch(vos);
        return R.ok();
    }
    //删除 自定义批量删除！！
    @PostMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody AttrGroupRelationVo[] vos){
        attrService.deleteRelation(vos);
        return R.ok();
    }
    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
 //   @RequiresPermissions("commodit:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params,@PathVariable("catelogId") Long catelogId){
//        PageUtils page = attrGroupService.queryPage(params);
        PageUtils page = attrGroupService.queryPage(params, catelogId);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
  //  @RequiresPermissions("commodit:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId =attrGroup.getCatelogId();
    Long[] path=    categoryService.findCatelogPath(catelogId);
        attrGroup.setCatelogPath(path);
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("commodit:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("commodit:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("commodit:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
