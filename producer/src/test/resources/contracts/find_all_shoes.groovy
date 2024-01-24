package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return all shoes"

    request {
        url "/shoes"
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body (
            [
                [
                     id: 1 ,
                     name: "Test Shoe 1" ,
                     size: 38 ,
                     color: "brown"
                ], [
                    id: 2 ,
                    name: "Test Shoe 2" ,
                    size: 40 ,
                    color: "black"
                ]
            ]
        )
    }
}