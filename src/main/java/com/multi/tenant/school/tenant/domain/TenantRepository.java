package com.multi.tenant.school.tenant.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TenantRepository extends CrudRepository<Tenant, UUID> {
    Optional<Tenant> findOneByProductName(String productName);

    boolean existsByProductName(String productName);
}
