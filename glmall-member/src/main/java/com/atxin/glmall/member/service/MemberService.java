package com.atxin.glmall.member.service;

import com.atxin.glmall.member.exception.PhoneException;
import com.atxin.glmall.member.exception.UsernameException;
import com.atxin.glmall.member.vo.MemberUserLoginVo;
import com.atxin.glmall.member.vo.MemberUserRegisterVo;
import com.atxin.glmall.member.vo.SocialUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atxin.common.utils.PageUtils;
import com.atxin.glmall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author xinjie
 * @email 2811252742@qq.com
 * @date 2022-08-09 08:50:48
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void register(MemberUserRegisterVo vo);

    void checkPhoneUnique(String phone) throws PhoneException;

    void checkUserNameUnique(String userName) throws UsernameException;

    MemberEntity login(MemberUserLoginVo vo);



    MemberEntity login(SocialUser socialUser) throws Exception;

    MemberEntity login(String accessTokenInfo);
}

