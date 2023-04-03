package com.example.springcloudcontractdemo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FraudCheckRequest {
    @JsonProperty("client.id")
    String clientId;
    int loanAmount;

}
