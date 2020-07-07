package com.budi.springcloud.controller;

import com.budi.springcloud.domain.CommResult;
import com.budi.springcloud.domain.Order;
import com.budi.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommResult create(Order order)
    {
        orderService.create(order);
        return new CommResult(200,"订单创建成功");
    }
}
