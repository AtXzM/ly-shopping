package com.atxin.glmall.commodit.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.atxin.glmall.commodit.dao.entity.ProductAttrValueEntity;
import com.atxin.glmall.commodit.service.ProductAttrValueService;
import com.atxin.glmall.commodit.vo.AttrRespVo;
import com.atxin.glmall.commodit.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atxin.glmall.commodit.service.AttrService;
import com.atxin.common.utils.PageUtils;
import com.atxin.common.utils.R;



/**
 * 商品属性
 *
 * @author xinjie
 * @email 2811252742@qq.com
 * @date 2022-08-07 19:28:47
 */
@RestController
@RequestMapping("commodit/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @Autowired
    ProductAttrValueService productAttrValueService;

    ///product/attr/info/{attrId}

    // /product/attr/base/listforspu/{spuId}
    //查出spu所有规格参数
    @GetMapping("/base/listforspu/{spuId}")
    public R baseAttrlistforspu(@PathVariable("spuId") Long spuId){

        List<ProductAttrValueEntity> entities = productAttrValueService.baseAttrlistforspu(spuId);

        return R.ok().put("data",entities);
    }

    //规格参数分页查询
    @GetMapping("/{attrType}/list/{catelogId}")
    public R baseAttrList(@RequestParam Map<String, Object> params,
                     @PathVariable("catelogId") Long catelogId,
    @PathVariable("attrType") String type){
     PageUtils page = attrService.queryBaseAttrPage(params, catelogId,type);
     return R.ok().put("page", page);
}

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("commodit:attr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
  //  @RequiresPermissions("commodit:attr:info")
    public R info(@PathVariable("attrId") Long attrId){
//		AttrEntity attr = attrService.getById(attrId);
        AttrRespVo respVo =attrService.getAttrInfo(attrId);
        return R.ok().put("attr", respVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("commodit:attr:save")
    public R save(@RequestBody AttrVo attr){
		attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改  补充遗漏信息 respVo！
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("commodit:attr:update")
    public R update(@RequestBody AttrVo attr){
		attrService.updateAttr(attr);

        return R.ok();
    }
    //根据spuid批量更改属性
    ///commodit/attr/update/{spuId}
    @PostMapping("/update/{spuId}")
    public R updateSpuAttr(@PathVariable("spuId") Long spuId,
                           @RequestBody List<ProductAttrValueEntity> entities){

        productAttrValueService.updateSpuAttr(spuId,entities);

        return R.ok();
    }
    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("commodit:attr:delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
