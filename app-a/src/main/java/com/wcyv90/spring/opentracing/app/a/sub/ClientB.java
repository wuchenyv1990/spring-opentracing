package com.wcyv90.spring.opentracing.app.a.sub;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "client-b", url = "http://localhost:8082/")
public interface ClientB {

    @GetMapping("api-1")
    public String api1();

}
