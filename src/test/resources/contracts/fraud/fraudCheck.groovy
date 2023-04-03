package contracts.fraud

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            name("fraud")
            request {
                method 'PUT'
                url '/fraudcheck'
                body([
                        "client.id": $(regex('[0-9]{10}')),
                        loanAmount : 99999
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