package com.assessment.retail.discount.entrypoint.rest.throttling;

import io.github.bucket4j.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class PerClientRateLimitInterceptor implements HandlerInterceptor {

    private static final long REFILL_INTERVAL_NUMBER_OF_TOKENS = 1L;
    private static final long REFILL_INTERVAL_NUMBER_OF_MINUTES = 1L;
    private static final long BUCKET_BANDWIDTH_CAPACITY = 2L;
    private static final long NUMBER_OF_TOKENS_TO_BE_CONSUMED = 1L;
    private static final String BILL_BASER_URL = "/v1/url";

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private final Bucket sharedBucket = Bucket4j.builder()
            .addLimit(Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1))))
            .build();

    private static Bucket standardBucket() {

        return Bucket4j.builder().addLimit(Bandwidth.classic(BUCKET_BANDWIDTH_CAPACITY,
                        Refill.intervally(REFILL_INTERVAL_NUMBER_OF_TOKENS, Duration.ofMinutes(REFILL_INTERVAL_NUMBER_OF_MINUTES))))
                .build();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {

        if (request.getRequestURI().contains(BILL_BASER_URL)) {

            Bucket requestBucket;

            final String clientIpAddress = getClientIp(request);

            if (StringUtils.isNotBlank(clientIpAddress)) {

                requestBucket = this.buckets.computeIfAbsent(clientIpAddress, key -> standardBucket());
            } else {
                requestBucket = this.sharedBucket;
            }

            final ConsumptionProbe probe = requestBucket.tryConsumeAndReturnRemaining(NUMBER_OF_TOKENS_TO_BE_CONSUMED);

            if (probe.isConsumed()) {
                response.addHeader("X-Rate-Limit-Remaining",
                        Long.toString(probe.getRemainingTokens()));
                return true;
            }

            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.addHeader("X-Rate-Limit-Retry-After-Milliseconds",
                    Long.toString(TimeUnit.NANOSECONDS.toMillis(probe.getNanosToWaitForRefill())));

            return false;
        }

        return true;
    }

    private String getClientIp(final HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {

            remoteAddr = request.getHeader("X-FORWARDED-FOR");

            if (StringUtils.isEmpty(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
}
