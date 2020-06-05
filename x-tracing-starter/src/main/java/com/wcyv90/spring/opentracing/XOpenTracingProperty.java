package com.wcyv90.spring.opentracing;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("opentracing.jaeger")
public class XOpenTracingProperty {

    private boolean zipkinShared = true;
    private boolean logSpanDetail = false;

}
