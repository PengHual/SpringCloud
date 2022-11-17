package com.peng.springcloud.dao;



import com.peng.springcloud.pojo.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PaymentDao {
    public int addPayment(Payment payment);

    public Payment queryPaymentById(@Param("id") Long id);
}
