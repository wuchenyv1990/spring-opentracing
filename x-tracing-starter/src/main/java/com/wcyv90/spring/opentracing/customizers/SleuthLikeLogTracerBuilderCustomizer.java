package com.wcyv90.spring.opentracing.customizers;

import com.wcyv90.spring.opentracing.environment.SleuthLikeLogScopeManager;
import io.jaegertracing.internal.JaegerTracer;
import io.opentracing.contrib.java.spring.jaeger.starter.TracerBuilderCustomizer;

public class SleuthLikeLogTracerBuilderCustomizer implements TracerBuilderCustomizer {

    @Override
    public void customize(JaegerTracer.Builder builder) {
        builder.withScopeManager(new SleuthLikeLogScopeManager());
    }

}