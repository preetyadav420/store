package com.preet.store;

import org.springframework.stereotype.Service;

//@Service("Razorpay")
public class RazorpayService implements PaymentService{

    public void processPayment(int amount) {
        System.out.println("Razorpay: Amount: "+amount);
    }
}
