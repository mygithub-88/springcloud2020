package com.budi.springcloud.controller;

import com.budi.springcloud.entities.CommonResult;
import com.budi.springcloud.entities.Payment;
import com.budi.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

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
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment=paymentService.getPaymentById(id);
        if(payment!=null){
            return new CommonResult(200,"查询成功 port"+serverPort);
        }else{
            return new CommonResult(444,"查询失败 port"+serverPort);
        }
    }

    @GetMapping(value = "/payment/dicovery")
    public Object discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element:services)
        {
            log.info("----elemnet:"+element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:instances)
        {
            log.info("instance-id:"+instance.getInstanceId()+"\t"+"hostname:"+instance.getHost()+"\t"+"port:"+instance.getPort()+"\t"+"url:"+instance.getUri());
        }
        return  this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymenLB()
    {
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  serverPort;
    }
    /**
     * 以下方法供zipkin学习的时候使用
     *
     */
    @GetMapping("/payment/zipkin")
    public String paymentZipkin()
    {
        return "hello im zipkin ,welcome to zipkin server";
    }
}
