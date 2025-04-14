package com.multi.tenant.school.infra;

public class TenantAliasNotFoundException extends RuntimeException {
    public TenantAliasNotFoundException(String message) {
        super(message);
    }
}
