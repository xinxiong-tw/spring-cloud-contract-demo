package com.example.springcloudcontractdemo;

import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.*;

public class FraudBase {
    @BeforeAll
    static void setup() {
        FraudService stubFraudService = mock(FraudService.class);
        doReturn(true).when(stubFraudService).isFraud(argThat(it -> it.getLoanAmount() > 90000));
        doReturn(false).when(stubFraudService).isFraud(argThat(it -> it.getLoanAmount() <= 90000));
        RestAssuredWebTestClient.standaloneSetup(new FraudController(stubFraudService));
    }
}
