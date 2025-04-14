package com.multi.tenant.school.envie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnvieRepository extends JpaRepository<Envie,Long> {
    Optional<Envie> findOneByName(String name);
}
