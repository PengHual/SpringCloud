package com.peng.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;


/**
 * 手写轮询
 */

public interface LoadBalancer {

    ServiceInstance instances(List<ServiceInstance> serviceInstances);

}
