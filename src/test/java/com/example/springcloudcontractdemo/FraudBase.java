package com.example.springcloudcontractdemo;

import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@ExtendWith(RestDocumentationExtension.class)
public class FraudBase {

    @BeforeEach
    public void beforeEach(TestInfo info, RestDocumentationContextProvider restDocumentation) {
        FraudService stubFraudService = mock(FraudService.class);
        doReturn(true).when(stubFraudService).isFraud(argThat(it -> it.getLoanAmount() > 90000));
        doReturn(false).when(stubFraudService).isFraud(argThat(it -> it.getLoanAmount() <= 90000));

        WebTestClient.Builder builder = WebTestClient.bindToController(new FraudController(stubFraudService))
                .configureClient()
                .filter(documentationConfiguration(restDocumentation))
                .entityExchangeResultConsumer(document(getClass().getSimpleName() + "_" + info.getTestMethod().get().getName()));
        RestAssuredWebTestClient.standaloneSetup(builder);
    }
}
