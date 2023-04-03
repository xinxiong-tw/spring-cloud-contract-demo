package com.example.springcloudcontractdemo;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/fraudcheck")
public class FraudController {

    private final FraudService fraudService;

    public FraudController(FraudService fraudService) {
        this.fraudService = fraudService;
    }

    @PutMapping
    Mono<FraudCheckResponse> fraudCheck(@RequestBody FraudCheckRequest fraudRequest) {
        return Mono.just(fraudRequest).map(it -> {
            if (fraudService.isFraud(it)) {
                return new FraudCheckResponse("FRAUD", "Amount too high");
            }
            return new FraudCheckResponse("OK", "");
        });
    }

}
