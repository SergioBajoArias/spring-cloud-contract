package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return shoe by id=1"

    request {
        url "/shoes/1"
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body (
                id: 1,
                name: "Test Shoe 1",
                size: 38,
                color: "brown"
        )
    }

}