package com.atxin.glmall.commodit.controller;

import java.util.Arrays;
import java.util.Map;


import com.atxin.common.valid.UpdateGroup;
import com.atxin.common.valid.UpdateStatusGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atxin.glmall.commodit.dao.entity.BrandEntity;
import com.atxin.glmall.commodit.service.BrandService;
import com.atxin.common.utils.PageUtils;
import com.atxin.common.utils.R;


/**
 * 品牌
 *
 * @author xinjie
 * @email 2811252742@qq.com
 * @date 2022-08-07 19:28:47
 */
@RestController
@RequestMapping("commodit/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("commodit:brand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
  //  @RequiresPermissions("commodit:brand:info")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("commodit:brand:save")
    public R save(@Validated @RequestBody BrandEntity brand /*,BindingResult result*/){
//        if(result.hasErrors()){
//            Map<String,String> map=new HashMap<>();
//            result.getFieldErrors().forEach((item)->{
//                String message=item.getDefaultMessage();
//                String field=item.getField();
//                map.put(field,message);
//            });
//            R.error(400,"提交的数据不合法").put("data",map);
//        }
//	else
	brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("commodit:brand:update")
    public R update(@Validated(UpdateGroup.class) @RequestBody BrandEntity brand){
		brandService.updateDetail(brand);

        return R.ok();
    }
    /**
     * 修改状态
     */
    @RequestMapping("/update/status")
    //  @RequiresPermissions("commodit:brand:update")
    public R updateStatus(@Validated(UpdateStatusGroup.class) @RequestBody BrandEntity brand){
        brandService.updateById(brand);

        return R.ok();
    }
    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("commodit:brand:delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
