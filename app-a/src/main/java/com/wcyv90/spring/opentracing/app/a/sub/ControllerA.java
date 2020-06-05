package com.wcyv90.spring.opentracing.app.a.sub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ControllerA {

    @Autowired
    private ClientB clientB;

    @GetMapping("api-1")
    public String api1() {
        log.info("access controller A api-1");
        return clientB.api1();
    }

    @GetMapping("api-2")
    public String api2() {
        log.info("access controller A api-2");
        return "controller a api-2 message";
    }

}
