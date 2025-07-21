package com.preet.store;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//@Service
public class OrderService {
    PaymentService paymentService;
    public OrderService( PaymentService paymentService)
    {
        this.paymentService = paymentService;
    }

    public void processPaymment()
    {
        paymentService.processPayment(10);
    }
}