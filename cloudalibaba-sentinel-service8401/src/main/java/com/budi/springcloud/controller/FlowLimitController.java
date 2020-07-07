package com.budi.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testa")
    public String testA(){
        return "+++=testA";
    }
    @GetMapping("/testb")
    public String testB()
    {
        return "-----testB";
    }
    @GetMapping("/testd")
    public String testD()
    {
        log.info("测试testd RT");
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "testd";
    }
    @GetMapping("/teste")
    public String testE()
    {
        int a=10/0;
        return "teste测试异常数";
    }

    /**
     * 热点规则
     * @param p1
     * @param p2
     * @return
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2)
    {
        return "test Hot key";
    }
    public String deal_testHotKey(String p1, String p2, BlockException exception)
    {
        return "deal_testHotKey";
    }
}
