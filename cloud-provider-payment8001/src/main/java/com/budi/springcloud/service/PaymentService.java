package com.budi.springcloud.service;

import com.budi.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int creat(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
