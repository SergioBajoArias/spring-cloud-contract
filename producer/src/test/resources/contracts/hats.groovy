package contracts

import org.springframework.cloud.contract.spec.Contract

[
    Contract.make {
        description "should return all hats"

        request {
            url "/hats"
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
                                    name: "Test Hat 1" ,
                                    size: 10 ,
                                    color: "striped"
                            ], [
                                    id: 2 ,
                                    name: "Test Hat 2" ,
                                    size: 7 ,
                                    color: "green"
                            ]
                    ]
            )
        }
    },

    Contract.make {
        description "should return hat by id=1"

        request {
            url "/hats/1"
            method GET()
        }

        response {
            status OK()
            headers {
                contentType applicationJson()
            }
            body (
                    id: 1,
                    name: "Test Hat 1",
                    size: 10,
                    color: "striped"
            )
        }

    },

    Contract.make {
        description "should return hat by id=1"

        request {
            url "/hats/2"
            method GET()
        }

        response {
            status OK()
            headers {
                contentType applicationJson()
            }
            body (
                    id: 2,
                    name: "Test Hat 2",
                    size: 7,
                    color: "green"
            )
        }

    },

    Contract.make {
        description "should return 404 for hat id=3"

        request {
            url "/hats/3"
            method GET()
        }

        response {
            status 404
        }

    }
]