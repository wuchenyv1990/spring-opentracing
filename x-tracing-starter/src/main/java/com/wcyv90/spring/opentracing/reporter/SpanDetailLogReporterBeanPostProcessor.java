package com.wcyv90.spring.opentracing.reporter;

import io.jaegertracing.internal.reporters.CompositeReporter;
import io.jaegertracing.spi.Reporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
public class SpanDetailLogReporterBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CompositeReporter) {
            CompositeReporter compositeReporter = (CompositeReporter) bean;
            Optional.ofNullable(ReflectionUtils.findField(CompositeReporter.class, "reporters"))
                    .ifPresent(field -> {
                        try {
                            ReflectionUtils.makeAccessible(field);
                            List<Reporter> reporters = (List<Reporter>) field.get(compositeReporter);
                            reporters.add(new SpanDetailLogReporter());
                        } catch (IllegalAccessException e) {
                            log.error("Set SpanDetailLogReporter failed.");
                        }
                    });
        }
        return bean;
    }
}
