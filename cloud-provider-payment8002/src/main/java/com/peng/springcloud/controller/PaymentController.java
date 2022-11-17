package com.peng.springcloud.controller;




import com.peng.springcloud.pojo.CommonResult;
import com.peng.springcloud.pojo.Payment;
import com.peng.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
//@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/add")
    public CommonResult addPayment(@RequestBody Payment payment){

        int result = paymentService.addPayment(payment);
        log.info(".......插入结果："+result);
        if (result > 0){
            return new CommonResult(200,"插入数据库成功,serverPort:"+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> queryPayment(@PathVariable("id") Long id){

         Payment payment = paymentService.queryPaymentById(id);
        log.info(".......查询结果："+payment+"\t"+"哈哈~咯");
        if (payment !=null ){
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        }else {
            return new CommonResult(444,"没有对应的记录,查询ID："+id,null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }


}
