package com.assessment.retail.discount.infrastructure.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MongoAuditing implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {

        final Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        String uname = "system-default-user";

        if (authentication != null) {
            uname = authentication.getName();
        }
        return Optional.of(uname);
    }
}
