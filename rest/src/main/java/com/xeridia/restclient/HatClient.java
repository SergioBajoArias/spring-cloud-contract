package com.xeridia.restclient;

import com.xeridia.model.Hat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "hatClient", url = "${services.url.hat}")
public interface HatClient {
    @GetMapping("/hats/{id}")
    Hat findHatById(@PathVariable("id") Long id);
}
