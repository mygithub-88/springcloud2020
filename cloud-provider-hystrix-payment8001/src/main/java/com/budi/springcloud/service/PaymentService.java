package com.budi.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PaymentService {
    /**
     * 正常访问 肯定ok
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id)
    {
        return "线程池： "+Thread.currentThread().getName()+" paymentInfo_OK id: "+id+"/t";
    }
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000")
    })
    public String paymentInfo_TimeOut(Integer id)
    {
        //int a =10/0; //测试报错时Hystrix如何处理
        try {
            Thread.sleep(1000);
            //Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： "+Thread.currentThread().getName()+" paymentInfo_TimeOut id: "+id+"/t"+"耗时3秒钟";
    }
    public String paymentInfo_TimeOutHandler(Integer id)
    {
        return "线程池： "+Thread.currentThread().getName()+" paymentInfo_TimeOut id: "+id+"/t"+"我是8001兜底的方法，上边的业务处理太LB,只能靠我了";

    }

    //====服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少次后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id<0){
            throw new RuntimeException("*********id 不能为负数");
        }
        String serialNumber= IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号:"+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能为负，请稍后再试 id:"+id;
    }
}
