package com.peng.springcloud.service;


import com.peng.springcloud.pojo.Payment;
import org.apache.ibatis.annotations.Param;


public interface PaymentService  {
    public int addPayment(Payment payment);

    public Payment queryPaymentById(@Param("id") Long id);

}
