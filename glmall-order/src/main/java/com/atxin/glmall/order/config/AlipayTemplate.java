package com.atxin.glmall.order.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import com.atxin.glmall.order.vo.PayVo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AlipayTemplate {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public String app_id="2021000120617052";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public String merchant_private_key="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCY9WVr8ez2G1Ckd76z8qjt2VnOqKnRXEgTXuQFBzAqN+Ij2rw8YWi2eAZIb6iY+Dr1iMEW6IUsW/Yw9RH097gqdQtD7oSwGaEnPw/ZqIcZ5qQnR+ov8RilO2qjL+vZSvTp49idzEC+KUafWilFKpGUsZxnmJ392mLxaA0c9yDtJUsgEuLDY+Vdf1E13U5Y0GtPTkp/dO57jr2mHM7EDw9NdD0T/OuomEDHL3nKkOpu8MRHDk1kyVwO/07kr3gDIvPj+wQQvr31xJMel7SFSZFdkBBj42IX+RcCH3vUUQmEFvqVJ3mWY13YXb5KYPGQ5AECkAfMVN4Y3FkxLqg+05vFAgMBAAECggEAIWCjQhvnpoitX0ZWEfRo0oY0Z7k8XVgyzi9NuJ8p7nyFV2ea3d4vuGmWjWX4N3ErhHrUD8irjtN+dI513avUv0cNL0S0Ja1iEitbrRZubMN8nKm/u7hztVKy4WtKnv8RVJyIn1573kMpiwpkqRgx7m2XgK+4AjYOkzkua1yuDbFWgVYe2C2lTS/yoKSRlwUZZsQk/VFKmIs6dWKYNWN7k0GNsb32Ili3HQkin9803od3B45bpfpJGRjLMuE8OE4Fv9m/uLEbHr3erKbzvXnFMfVXpukRJFibkKEhQsAUAY+MquDbPTxyrXHzUUvAQ2q8PTQbCNUEofwcrg0NJzbvIQKBgQDyRdxSx756IDVPU/xfJf9MJ/YLW85LbWC16DOcbnEcRW9t7VumpzbXKjVYfR6j79+m3VRPuT+T6a6PJTnCne03ZP0MnD0e/XzzGYZzoPcahYM0GHf4iYqhGYwS6XeQwWZ3rhQdJuIel/GFiiYeyocejqaF4ukGx+Md4eYdQ3/VuQKBgQChoArGEvG9UVn6bM24+/TTiv0r582pjmyzeLkj3vCduHcSOFcOC1YJizcI6+PKDe1yR9C9PSmruvNWTMLlLlJSr7ol4ctJTJM2gLuuL9GaGHc7/O3tLISx7QC24LGTOXOgYK9B56Se5dVNzEIHAasZeyfjPWSnjooFDKAcNOB8bQKBgQDXAIh2Li0r1El7DN160w2tWAlSKzb4WSjqRhcHTDu1CynINEyRqPNSH3MYBHGcqtOgEeswlQWHDmqLPk30GJLcBhNLjZvwVKT+WDiphL/GGZZGNTDCr80HCtT0M5yYKgn1S9ctx8QDwnPkPqV1zeX7iDzftG94RQuZCVBdx/k6gQKBgBAK26bncHkzHvQ9YnoPADETJaf6/S3sRhvWSsIlucpd6eu0DegF9gdCezb2ewiuMzhZqn1uGbnC965ENkAbRe6RJK/n5o6i/CW3x2k1s99QuF0K8O7OsWkbtitwyA9t720my56GCJABDhgHwzIA12PTFpV2nYwIGOkBZM8H7JRNAoGAb0KeWOigZmuvGzxBvXyxkK8/3w0vy++zqI4EShbZRWCFtSOdIntht5/SWNXRvB2DcpcwK/x3zsbB/NHKqly3mzOumR95pClxmE4aRr5C7bHmptJCd6Trt1jlilVo8n1HAlpyxsChakc+MHcCQoSm7AM9dH3YEf1zLJZ+E7nXCag=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public String alipay_public_key="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7MBQ5LMdIaKzJMJJfK6SpNGNHXu2oOLHY/L6ZPdFI0iDYzpGMFS5Ri5SDbKimyIfDijTqigsiHyJ47w0OE8eXJy4sIS3oJ+GpZesyNmTxD9wLo0YSvg372IfpjHfi/zqqHn3d8v+ww/KNlc2p4LUHmjfjqQ5S4oRXcOzCa3PEnns8pMSrVdemScLtAWHy07On5+up6lsfvn0XNGAuDU6TO8THuNJBbXmkPwwXwX/higu50tDjlga7zBzHwV1UCXFpi8lUl6kQWaLtzsAUFQjrbfNCMmPNsKMslLGhg6zeJxut2M+Hh3ZPF4qj41GL0Sm4ZG/nFXVJQmBltyj0DeKowIDAQAB";

    // 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    public String notify_url="http://hjl.mynatapp.cc/payed/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    public String return_url="http://member.gulimall.com/memberOrder.html";

    // 签名方式
    private  String sign_type="RSA2";

    // 字符编码格式
    private  String charset="utf-8";

    //订单超时时间
    private String timeout = "1m";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    public String gatewayUrl="https://openapi.alipaydev.com/gateway.do";

    public  String pay(PayVo vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+timeout+"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        System.out.println("支付宝的响应："+result);

        return result;

    }
}
