package com.udemy.report_ms.beans;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
//import ch.qos.logback.core.util.Duration;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class CircuitBreakerBean {

	/*Esto es para configurar el circuit breaker**/
	//CB = circuit breaker
	//Customizer es una interfaz funcional porque eso solo tiene un metodo 
    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> globalCustomCB() {
        var circuitBreakerConfig = CircuitBreakerConfig
                .custom()
                .failureRateThreshold(60)
                .waitDurationInOpenState(Duration.ofSeconds(1))
                .slidingWindowSize(200)
                .build();
        	
        // Declara el tiempo limite para una llamada antes de declarla como fallida
            var timeLimiterConfig = TimeLimiterConfig
                    .custom()
                    .timeoutDuration(Duration.ofSeconds(8))
                    .build();

            //el id de mi circuitbreaker
            return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                    .timeLimiterConfig(timeLimiterConfig)
                    .circuitBreakerConfig(circuitBreakerConfig)
                    .build()
            );
            }
}
