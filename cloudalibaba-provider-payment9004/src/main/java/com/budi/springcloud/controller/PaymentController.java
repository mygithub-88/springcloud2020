package com.budi.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
class PaymentControler {
    @Value("${server.port}")
    private String serverPort;
    @GetMapping("/paymentSQL/{id}")
    public String paymentSQL(@PathVariable("id")Long id)
    {
        return "serverPort:"+serverPort+" id:"+id;
    }
}
