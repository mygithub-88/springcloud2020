package com.budi.springcloud.controller;

import com.budi.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod") //全局降级注解标签
public class PaymentHystrixController {
    @Resource
    private PaymentHystrixService hystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")

    //此方法可以测试 service接口继承后 服务降级是否可用
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        //int a=10/0; //测试hystrix服务降级是否可用
       return hystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000")
    })*/
    @HystrixCommand //全局降级注解标签需要
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id)
    {
        return hystrixService.paymentInfo_TimeOut(id);
    }
    public String paymentInfo_TimeOutHandler(Integer id)
    {
        return "线程池： "+Thread.currentThread().getName()+" paymentInfo_TimeOut id: "+id+"/t"+"我是80客户端兜底的方法，上边的业务处理太LB,只能靠我了";
    }

    public String payment_Global_FallbackMethod()
    {
        return "信息处理异常，请稍后再试！";
    }
}
