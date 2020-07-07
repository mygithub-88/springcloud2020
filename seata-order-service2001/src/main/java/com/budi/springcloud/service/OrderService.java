package com.budi.springcloud.service;

import com.budi.springcloud.domain.Order;
import org.springframework.stereotype.Service;

public interface OrderService {
    void create(Order order);
}
