package com.preet.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

//@Primary
//@Service("Paytm")
public class PaytmService implements PaymentService{

    @Value("${paytm.account.balance: 0}")
    private int balance;

    @Value("${paytm.name}")
    private String name;

    @Value("${paytm.account.loan}")
    private int loan;

    public void processPayment(int amount) {
        System.out.println("Paytm: Name: "+ name);
        System.out.println("Paytm: Balance: "+ balance);
        System.out.println("Paytm: Loan: "+ loan);
        System.out.println("Paytm: Amount: "+ amount);
    }
}
