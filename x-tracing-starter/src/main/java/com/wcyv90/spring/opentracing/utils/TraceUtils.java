package com.wcyv90.spring.opentracing.utils;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.contrib.spring.cloud.SpanUtils;
import io.opentracing.tag.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class TraceUtils {

    @Autowired
    private Tracer tracer;

    //for some case code not tracked, start a span if needed.
    public <T> T executeInSpanManually(Supplier<T> action, String spanName) {
        Span span = tracer.buildSpan(spanName)
                .withTag(Tags.COMPONENT.getKey(), "manual")
                .start();
        try {
            try (Scope scope = tracer.activateSpan(span)) {
                return action.get();
            }
        } catch (Exception ex) {
            SpanUtils.captureException(span, ex);
            throw ex;
        } finally {
            span.finish();
        }
    }

}
