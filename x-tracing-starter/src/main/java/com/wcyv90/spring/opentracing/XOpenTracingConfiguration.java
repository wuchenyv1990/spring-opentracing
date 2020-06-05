package com.wcyv90.spring.opentracing;

import com.wcyv90.spring.opentracing.customizers.SleuthLikeLogTracerBuilderCustomizer;
import com.wcyv90.spring.opentracing.customizers.ZipkinSharedTracerBuilderCustomizer;
import com.wcyv90.spring.opentracing.reporter.SpanDetailLogReporterBeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(XOpenTracingProperty.class)
@ConditionalOnProperty("opentracing.jaeger.enabled")
public class XOpenTracingConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "opentracing.jaeger", value = "zipkin-shared", havingValue = "true", matchIfMissing = true)
    public ZipkinSharedTracerBuilderCustomizer zipkinSharedTracerBuilderCustomizer() {
        return new ZipkinSharedTracerBuilderCustomizer();
    }

    @Bean
    @ConditionalOnProperty(prefix = "opentracing.jaeger", value = "sleuth-like-log", havingValue = "true", matchIfMissing = true)
    public SleuthLikeLogTracerBuilderCustomizer sleuthLikeLogTracerBuilderCustomizer(XOpenTracingProperty xOpenTracingProperty) {
        return new SleuthLikeLogTracerBuilderCustomizer();
    }

    @Bean
    @ConditionalOnProperty(prefix = "opentracing.jaeger", value = "log-span-detail")
    public SpanDetailLogReporterBeanPostProcessor spanDetailLogReporterBeanPostProcessor () {
        return new SpanDetailLogReporterBeanPostProcessor();
    }

}
