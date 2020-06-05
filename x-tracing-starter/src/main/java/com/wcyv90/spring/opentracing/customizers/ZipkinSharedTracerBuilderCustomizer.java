package com.wcyv90.spring.opentracing.customizers;

import io.jaegertracing.internal.JaegerTracer;
import io.opentracing.contrib.java.spring.jaeger.starter.TracerBuilderCustomizer;

public class ZipkinSharedTracerBuilderCustomizer implements TracerBuilderCustomizer {

    @Override
    public void customize(JaegerTracer.Builder builder) {
        builder.withZipkinSharedRpcSpan();
    }

}
