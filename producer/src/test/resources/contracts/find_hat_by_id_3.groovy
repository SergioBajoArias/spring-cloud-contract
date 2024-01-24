package contracts

import org.springframework.cloud.contract.spec.Contract

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