package com.preet.store;

import org.springframework.stereotype.Service;


public interface PaymentService {
    public void processPayment(int amount);
}
