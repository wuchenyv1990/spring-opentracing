package com.wcyv90.spring.opentracing.reporter;

import com.wcyv90.spring.opentracing.JsonMapper;
import io.jaegertracing.internal.JaegerSpan;
import io.jaegertracing.spi.Reporter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class SpanDetailLogReporter implements Reporter {

    @Override
    public void report(JaegerSpan span) {
        log.info(JsonMapper.dumps(SpanDetail.from(span)));
    }

    @Override
    public void close() {

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class SpanDetail {

        private String traceId;
        private String spanId;
        private String parentSpanId;
        private Map<String, Object> tags;
        private Map<String, String> baggage;

        public static SpanDetail from(JaegerSpan jaegerSpan) {
            Iterator<Map.Entry<String, String>> iterator = jaegerSpan.context().baggageItems().iterator();
            Map<String, String> baggage = new HashMap<>();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                baggage.put(entry.getKey(), entry.getValue());
            }
            return SpanDetail.builder()
                    .traceId(jaegerSpan.context().getTraceId())
                    .spanId(jaegerSpan.context().toSpanId())
                    .parentSpanId(Long.toHexString(jaegerSpan.context().getParentId()))
                    .tags(jaegerSpan.getTags())
                    .baggage(baggage)
                    .build();
        }

    }

}
