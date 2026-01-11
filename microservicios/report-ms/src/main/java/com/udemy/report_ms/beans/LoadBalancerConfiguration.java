package com.udemy.report_ms.beans;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

//Para convertirlo en bean y se lea desde el inicio 
//@Configuration
@Slf4j

public class LoadBalancerConfiguration {

	
    @Bean
    public ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext context) {
        log.info("Configuring load balancer");
        return ServiceInstanceListSupplier
                .builder()
                //Para hacerlo reactivo o no (esta es la forma normal)
                .withBlockingDiscoveryClient()
                //.withSameInstancePreference()
                .build(context);
    }
}
