package com.multi.tenant.school.api;

import com.multi.tenant.school.tenant.domain.Tenant;
import com.multi.tenant.school.tenant.domain.TenantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/tenants")
@AllArgsConstructor
public class TenantController {
    private final TenantService tenantService;

    @PostMapping
    public ResponseEntity<Tenant> create(@RequestBody Tenant tenant) {
        Tenant created = tenantService.createTenant(tenant);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
