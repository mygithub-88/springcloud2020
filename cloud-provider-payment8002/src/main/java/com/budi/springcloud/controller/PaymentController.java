package com.budi.springcloud.controller;

import com.budi.springcloud.entities.CommonResult;
import com.budi.springcloud.entities.Payment;
import com.budi.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result=paymentService.creat(payment);
        System.out.println("查询结果："+result);
        if(result>0){
            return new CommonResult(200,"插入成功...serverport"+serverPort);
        }else{
            return new CommonResult(444,"插入失败serverport"+serverPort);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment=paymentService.getPaymentById(id);
        if(payment!=null){
            return new CommonResult(200,"查询成功 port"+serverPort);
        }else{
            return new CommonResult(444,"查询失败 port"+serverPort);
        }
    }
    @GetMapping(value = "/payment/lb")
    public String getPaymenLB()
    {
        return serverPort;
    }
}
