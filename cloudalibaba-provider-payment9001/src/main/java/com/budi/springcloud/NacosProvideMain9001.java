package com.budi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosProvideMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(NacosProvideMain9001.class,args);
    }
}
