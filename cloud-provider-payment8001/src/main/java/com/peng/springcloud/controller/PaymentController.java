package com.peng.springcloud.controller;




import com.netflix.appinfo.InstanceInfo;

import com.peng.springcloud.pojo.CommonResult;
import com.peng.springcloud.pojo.Payment;
import com.peng.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Component
//@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/add")
    public CommonResult addPayment(@RequestBody Payment payment) {

        int result = paymentService.addPayment(payment);
        log.info(".......插入结果：" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort:" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> queryPayment(@PathVariable("id") Long id) {

        Payment payment = paymentService.queryPaymentById(id);
        log.info(".......查询结果：" + payment + "\t" + "哈哈~咯");
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, payment);
        } else {
            return new CommonResult(444, "查询失败", null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){

        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("**********element:"+element);

        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance Instance : instances) {
            log.info(Instance.getServiceId()+"\t"+Instance.getHost()+"\t"+Instance.getPort()+"\t"+Instance.getUri());

        }

        return this.discoveryClient;
    }


@GetMapping(value = "/payment/lb")
public String getPaymentLB(){
    return serverPort;
}


    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
     try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e)
        {e.printStackTrace();}
    return serverPort;
    }
}