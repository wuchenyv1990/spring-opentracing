package com.wcyv90.spring.opentracing.app.b.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ControllerB {

    @Autowired
    private ClientA clientA;

    @GetMapping("api-1")
    public String api1() {
        log.info("access controller B api-1");
        return "controller b api-1 message";
    }

    @GetMapping("api-2")
    public String api2() {
        log.info("access controller B api-2");
        return clientA.api2();
    }

}
