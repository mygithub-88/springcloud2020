package com.budi.springcloud.impl;

import com.budi.springcloud.dao.PaymentDao;
import com.budi.springcloud.entities.Payment;
import com.budi.springcloud.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;
    public int creat(Payment payment)
    {
        return paymentDao.creat(payment);
    }
    public Payment getPaymentById(@Param("id") Long id)
    {
        return paymentDao.getPaymentById(id);
    }
}
