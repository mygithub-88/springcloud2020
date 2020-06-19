package com.budi.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.util.UIUtil;

import java.util.UUID;

@RestController
public class PaymentConsulController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/consul")
    public String paymentConsul(){
        return "spring cloud of consul:"+serverPort+"\t"+ UUID.randomUUID().toString();
    }
}
