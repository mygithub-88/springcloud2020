package com.budi.springcloud.service.impl;

import com.budi.springcloud.dao.OrderDao;
import com.budi.springcloud.domain.Order;
import com.budi.springcloud.service.AccountService;
import com.budi.springcloud.service.OrderService;
import com.budi.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService
{
    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;
    @Override
    public void create(Order order) {
        log.info("--------->开始创建订单");
        orderDao.create(order);

        log.info("--------->开始调用库存接口，做扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("--------->开始调用库存接口，扣减end");

        log.info("--------->开始调用账户接口，做扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("--------->开始调用库存接口，扣减end");

        //修改订单的状态 从0到1 1代表已经完成
        log.info("--------->修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("--------->修改订单状态结束");

    }
}
