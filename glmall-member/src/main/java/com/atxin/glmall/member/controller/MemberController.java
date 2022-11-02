package com.atxin.glmall.member.controller;

import java.util.Arrays;
import java.util.Map;


import com.atxin.common.exception.BizCodeEnum;
import com.atxin.glmall.member.exception.PhoneException;
import com.atxin.glmall.member.exception.UsernameException;
import com.atxin.glmall.member.feign.CouponFeignService;
import com.atxin.glmall.member.vo.MemberUserLoginVo;
import com.atxin.glmall.member.vo.MemberUserRegisterVo;
import com.atxin.glmall.member.vo.SocialUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.atxin.glmall.member.entity.MemberEntity;
import com.atxin.glmall.member.service.MemberService;
import com.atxin.common.utils.PageUtils;
import com.atxin.common.utils.R;



/**
 * 会员
 *
 * @author xinjie
 * @email 2811252742@qq.com
 * @date 2022-08-09 08:50:48
 */
@RestController
@RequestMapping("/member/member")
public class MemberController {
    @Autowired
    MemberService memberService;
    @Autowired
    CouponFeignService couponFeignService;

    //注册功能
    @PostMapping(value = "/register")
    public R register(@RequestBody MemberUserRegisterVo vo) {
      System.out.println(111111);
        try {
            memberService.register(vo);
            System.out.println("已成功");
        } catch (PhoneException e) {
            System.out.println("已成功00");
            return R.error(BizCodeEnum.PHONE_EXIST_EXCEPTION.getCode(),BizCodeEnum.PHONE_EXIST_EXCEPTION.getMessage());
        } catch (UsernameException e) {
            System.out.println("已成功11");
            return R.error(BizCodeEnum.USER_EXIST_EXCEPTION.getCode(),BizCodeEnum.USER_EXIST_EXCEPTION.getMessage());
        }
      System.out.println(2222222);
        return R.ok();
    }


    @PostMapping(value = "/login")
    public R login(@RequestBody MemberUserLoginVo vo) {

        MemberEntity memberEntity = memberService.login(vo);

        if (memberEntity != null) {
            return R.ok().setData(memberEntity);
        } else {
            return R.error(BizCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getCode(),BizCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getMessage());
        }
    }


    @PostMapping(value = "/oauth2/login")
    public R oauthLogin(@RequestBody SocialUser socialUser) throws Exception {

        MemberEntity memberEntity = memberService.login(socialUser);

        if (memberEntity != null) {
            return R.ok().setData(memberEntity);
        } else {
            return R.error(BizCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getCode(),BizCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getMessage());
        }
    }

//    @PostMapping(value = "/weixin/login")
//    public R weixinLogin(@RequestParam("accessTokenInfo") String accessTokenInfo) {
//
//        MemberEntity memberEntity = memberService.login(accessTokenInfo);
//        if (memberEntity != null) {
//            return R.ok().setData(memberEntity);
//        } else {
//            return R.error(BizCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getCode(),BizCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getMessage());
//        }
//    }
//    @RequestMapping("/coupons")
//    public R test(){
//
//        MemberEntity memberEntity=new MemberEntity();
//        memberEntity.setNickname("zhangsan");
//        R membercoupns=couponFeignService.membercoupons();
//        return R.ok().put("member",memberEntity).put("coupons",membercoupns.get("coupons"));
//    }
//    /**
//     * 列表
//     */
//    @RequestMapping("/list")
// //   @RequiresPermissions("member:member:list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = memberService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }
//
//
//    /**
//     * 信息
//     */
//    @RequestMapping("/info/{id}")
//  //  @RequiresPermissions("member:member:info")
//    public R info(@PathVariable("id") Long id){
//		MemberEntity member = memberService.getById(id);
//
//        return R.ok().put("member", member);
//    }
//
//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//  //  @RequiresPermissions("member:member:save")
//    public R save(@RequestBody MemberEntity member){
//		memberService.save(member);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//  //  @RequiresPermissions("member:member:update")
//    public R update(@RequestBody MemberEntity member){
//		memberService.updateById(member);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    //@RequiresPermissions("member:member:delete")
//    public R delete(@RequestBody Long[] ids){
//		memberService.removeByIds(Arrays.asList(ids));
//
//        return R.ok();
//    }

}
