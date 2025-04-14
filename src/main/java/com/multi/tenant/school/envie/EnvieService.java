package com.multi.tenant.school.envie;

import com.multi.tenant.school.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnvieService {
    private final EnvieRepository envieRepository;

    public Envie findByNameOrFail(String name) {
        return envieRepository.findOneByName(name).orElseThrow(EntityNotFoundException::new);
    }
}
