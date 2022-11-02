package com.atxin.glmall.ware.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.atxin.glmall.ware.vo.MergeVo;
import com.atxin.glmall.ware.vo.PurchaseDoneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atxin.glmall.ware.entity.PurchaseEntity;
import com.atxin.glmall.ware.service.PurchaseService;
import com.atxin.common.utils.PageUtils;
import com.atxin.common.utils.R;



/**
 * 采购信息
 *
 * @author xinjie
 * @email 2811252742@qq.com
 * @date 2022-08-09 09:37:26
 */
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {
    //完成采购单（标注采购项状态 失败原因）
    @PostMapping("/done")
    public  R finish(@RequestBody PurchaseDoneVo doneVo){
        purchaseService.done(doneVo);
        return R.ok();
    }
    //领取工作人员采购单
    @PostMapping("/received")
    public  R received(@RequestBody List<Long> ids){
        purchaseService.received(ids);
        return R.ok();
    }
    @Autowired
    private PurchaseService purchaseService;
    //合并采购单
    @PostMapping("/merge")
    public R merge(@RequestBody MergeVo mergeVo){
        purchaseService.mergePurchase(mergeVo);
        return R.ok();
    }
    //获取未领取过的采购单
    @RequestMapping("/unreceive/list")
    //   @RequiresPermissions("ware:purchase:list")
    public R unreceivelist(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryPageUnreceivePurchase(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("ware:purchase:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
  //  @RequiresPermissions("ware:purchase:info")
    public R info(@PathVariable("id") Long id){
		PurchaseEntity purchase = purchaseService.getById(id);

        return R.ok().put("purchase", purchase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("ware:purchase:save")
    public R save(@RequestBody PurchaseEntity purchase){
		purchaseService.save(purchase);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("ware:purchase:update")
    public R update(@RequestBody PurchaseEntity purchase){
		purchaseService.updateById(purchase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ware:purchase:delete")
    public R delete(@RequestBody Long[] ids){
		purchaseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
