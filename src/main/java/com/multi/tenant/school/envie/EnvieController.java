package com.multi.tenant.school.envie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.multi.tenant.school.infra.TenantInterceptor.TENANT_HEADER;

@Controller
@RequestMapping("/v1/envie")
public class EnvieController {
    private final EnvieRepository envieRepository;

    public EnvieController(EnvieRepository envieRepository) {
        this.envieRepository = envieRepository;
    }

    @GetMapping
    public List<EnvieResponse> findTenantEnvie(@RequestHeader(name = TENANT_HEADER) String name) {
        return envieRepository.findOneByName(name).stream()
                .map(EnvieResponse::convert)
                .toList();
    }
}
