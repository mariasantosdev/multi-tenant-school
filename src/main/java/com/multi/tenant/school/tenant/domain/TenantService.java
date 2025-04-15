package com.multi.tenant.school.tenant.domain;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@AllArgsConstructor
public class TenantService {

    private final DataSource dataSource;
    private final TenantRepository tenants;

    @Transactional
    public Tenant createTenant(Tenant tenant) {
        Tenant saved = tenants.save(tenant);
        initDatabase(tenant.getProductName());
        return saved;
    }

    public void initDatabase(String schema) {
        Flyway flyway = Flyway.configure()
                .locations("db/migration/envie")
                .dataSource(dataSource)
                .schemas(schema)
                .load();
        flyway.migrate();
    }

    public boolean existsByNamespace(String namespace) {
        return tenants.existsByProductName(namespace);
    }
}
