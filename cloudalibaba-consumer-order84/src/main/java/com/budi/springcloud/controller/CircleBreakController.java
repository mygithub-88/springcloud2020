package com.budi.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.budi.springcloud.entities.CommonResult;
import com.budi.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakController {
    public static final String SERVICE_URL="http://nacos-payment-provider";
    @Resource
    private RestTemplate restTemplate;
    @RequestMapping("/fallback/{id}")
    //@SentinelResource(value = "fallback")//什么都没有配置
    //@SentinelResource(value = "fallback",fallback = "fallbackHandler")//只配置fallback
    //@SentinelResource(value = "fallbakc",blockHandler = "blockHandler")//只配置blockhandle 只负责sentinel控制台违规
    @SentinelResource(value = "fallback",fallback = "fallbackHandler",blockHandler = "blockHandler")
    public String fallBack(@PathVariable("id")Long id)
    {
        String result = restTemplate.
                getForObject(SERVICE_URL + "/paymentSQL/" + id, String.class);
        if(id==4)
        {
            throw new IllegalArgumentException("非法的参数异常");
        }else if(result.isEmpty())
        {
            throw new NullPointerException("没有该ID的记录，空指针异常");
        }
        return result;
    }
    /**
     * 只配置fallback
     */
    public String fallbackHandler(@PathVariable("id")Long id,Throwable e) //参数必须带过来，否则会无效
    {
        return "fallbackHandler兜底方法发挥作用了。。。。。"+e.getMessage().toString();
    }
    /**
     * 只配置blockhandle
     */
    public String blockHandler(@PathVariable("id")Long id, BlockException exception)
    {
        return "blockHandler只处理控制台违规的方法来了";
    }
}
