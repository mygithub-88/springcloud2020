package com.budi.springcloud.controller;

import com.budi.springcloud.entities.CommonResult;
import com.budi.springcloud.entities.Payment;
import com.budi.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        return paymentFeignService.getPaymentById(id);
    }
    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentFeignTimeOut()
    {
        //客户端一般默认等待3秒钟 此方法测试超时错误 然后怎么解决
        return paymentFeignService.paymentFeignTimeOut();
    }
}
