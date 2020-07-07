package com.budi.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.budi.springcloud.entities.CommonResult;
import com.budi.springcloud.myhandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handelException")
    public CommonResult byResource()
    {
        return new CommonResult(202,"按照资源名称限流测试ok");
    }
    public CommonResult handelException(BlockException exception)
    {
        return new CommonResult(500,exception.getClass().getCanonicalName()+"服务不可用");
    }
    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl",blockHandlerClass = CustomerBlockHandler.class
    ,blockHandler = "handelException")
    public CommonResult byUrl()
    {
        return new CommonResult(200,"按照url限流ok");
    }

    //customerBlockHandler
    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler")
    public CommonResult customerBlockHandler()
    {
        return new CommonResult(200,"按客户自定义");
    }
}
