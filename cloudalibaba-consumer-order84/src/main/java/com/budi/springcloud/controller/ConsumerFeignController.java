package com.budi.springcloud.controller;

import com.budi.springcloud.service.PaymentService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConsumerFeignController {
    @Resource
    private PaymentService paymentService;
    @GetMapping("/consumer/openfeign/paymentSQL/{id}")
    public String paymentSQL(@PathVariable("id")Long id)
    {
       return paymentService.paymentSQL(id);
    }
}
