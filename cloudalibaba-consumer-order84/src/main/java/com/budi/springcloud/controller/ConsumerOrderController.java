package com.budi.springcloud.controller;

import com.budi.springcloud.config.RestempletConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ConsumerOrderController {
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/consumer/paymentSQL/{id}")
    public String getPayment(@PathVariable("id")Long id)
    {
        return restTemplate.getForObject("http://nacos-payment-provider"+"/paymentSQL/"+id,String.class);
    }
}
