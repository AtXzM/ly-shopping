package com.atxin.glmall.order.vo;

import com.atxin.glmall.order.entity.OrderEntity;

import lombok.Data;



@Data
public class SubmitOrderResponseVo {

    private OrderEntity order;

    /** 错误状态码 **/
    private Integer code;


}
