package com.multi.tenant.school.envie;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.multi.tenant.school.infra.TenantInterceptor.TENANT_HEADER;

@Controller
@RequestMapping("/v1/envie")
//TODO fazer dtos para ficar fofinho depois!
public class EnvieController {
    private final EnvieRepository envieRepository;
    private final EnvieService envieService;

    public EnvieController(EnvieRepository envieRepository, EnvieService envieService) {
        this.envieRepository = envieRepository;
        this.envieService = envieService;
    }

    @GetMapping
    public Envie findTenantEnvie(@RequestHeader(name = TENANT_HEADER) String name) {
        return envieService.findByNameOrFail(name);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Envie createEnvie(@RequestBody Envie envie) {
        return envieRepository.save(envie);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Envie updateEnvie(@RequestHeader(name = TENANT_HEADER) String name) {
        Envie envie = envieService.findByNameOrFail(name);
        return envieRepository.save(envieRepository.save(envie));
    }
}
