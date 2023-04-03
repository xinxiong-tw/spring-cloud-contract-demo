package com.example.springcloudcontractdemo;

import org.springframework.stereotype.Service;

@Service
public class FraudService {
    public boolean isFraud(FraudCheckRequest fraudCheckRequest) {
        return fraudCheckRequest.getLoanAmount() >= 90000;
    }
}
