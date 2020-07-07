package com.budi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AliProviderPaymentMain9003 {
    public static void main(String[] args) {
        SpringApplication.run(AliProviderPaymentMain9003.class,args);
    }
}
