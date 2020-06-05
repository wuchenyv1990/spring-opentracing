package com.wcyv90.spring.opentracing.app.b.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "client-a", url = "http://localhost:8081/")
public interface ClientA {

    @GetMapping("api-2")
    public String api2();

}
