package contracts

import org.springframework.cloud.contract.spec.Contract

[
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
    },

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

    },

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

    },

    Contract.make {
        description "should return 404 for shoe id=3"

        request {
            url "/shoes/3"
            method GET()
        }

        response {
            status 404
        }

    }
]