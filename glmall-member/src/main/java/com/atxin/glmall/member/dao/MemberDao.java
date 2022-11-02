package com.atxin.glmall.member.dao;

import com.atxin.glmall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author xinjie
 * @email 2811252742@qq.com
 * @date 2022-08-09 08:50:48
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
