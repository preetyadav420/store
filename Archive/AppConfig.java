package com.preet.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class AppConfig {

    @Value("${payment.service:Paytm}")
    private String service;

    @Bean
    public PaymentService paytm(){
        return new PaytmService();
    }

    @Bean
    public PaymentService razorpay(){
        return new RazorpayService();
    }

    @Bean
    public OrderService order(){
        //System.out.println("service : "+service);
        if(Objects.equals(service, "Razorpay"))
            return new OrderService(razorpay());
        else if(Objects.equals(service, "Paytm"))
                return new OrderService(paytm());
        else
            return null;
    }
}
