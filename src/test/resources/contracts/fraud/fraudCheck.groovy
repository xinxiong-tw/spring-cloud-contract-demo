package contracts.fraud

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            name("get fraud")
            request {
                method 'GET'
                url '/fraudcheck'
            }
            response {
                status OK()
                body "Hello"
            }
        },
        Contract.make {
            name("fraud")
            request {
                method 'PUT'
                url '/fraudcheck'
                body([
                        "client.id": $(regex('[0-9]{10}')),
                        loanAmount : 99999 // 贷款金额，不能超过 90000
                ])
                headers {
                    contentType('application/json')
                }
            }
            response {
                status OK()
                body([
                        fraudCheckStatus  : "FRAUD",
                        "rejection.reason": "Amount too high"
                ])
                headers {
                    contentType('application/json')
                }
            }
        },
        Contract.make {
            name("no fraud")
            request {
                method 'PUT'
                url '/fraudcheck'
                body([
                        "client.id": $(regex('[0-9]{10}')),
                        loanAmount : 10000
                ])
                headers {
                    contentType('application/json')
                }
            }
            response {
                status OK()
                body([
                        fraudCheckStatus  : "OK",
                        "rejection.reason": ""
                ])
                headers {
                    contentType('application/json')
                }
            }
        }
]