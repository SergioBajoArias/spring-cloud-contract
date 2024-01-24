package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return shoe by id=2"

    request {
        url "/shoes/2"
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body (
                id: 1,
                name: "Test Shoe 2",
                size: 40,
                color: "black"
        )
    }

}