package com.budi.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallBackService implements PaymentService {
    @Override
    public String paymentSQL(Long id) {
        return "我是给openfign调用兜底的方法";
    }
}
