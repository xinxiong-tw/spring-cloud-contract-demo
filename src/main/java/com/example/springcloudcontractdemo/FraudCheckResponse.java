package com.example.springcloudcontractdemo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FraudCheckResponse {
    String fraudCheckStatus;

    @JsonProperty("rejection.reason")
    String rejectionReason;
}
