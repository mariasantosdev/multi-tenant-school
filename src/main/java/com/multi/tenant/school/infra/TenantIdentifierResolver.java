package com.multi.tenant.school.infra;

import lombok.Builder;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    public static final String DEFAULT_TENANT = "alura";

    @Override
    public Object resolveCurrentTenantIdentifier() {
        return Optional.ofNullable(TenantContext.getTenantId()).orElse(DEFAULT_TENANT);
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
