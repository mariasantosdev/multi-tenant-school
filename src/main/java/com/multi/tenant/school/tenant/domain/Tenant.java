package com.multi.tenant.school.tenant.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tenant", schema = "core")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Builder
//TODO talvez adicionar outros atributos aqui que hoje estao no envie
public class Tenant {
    @Id
    @EqualsAndHashCode.Include
    private UUID id = UUID.randomUUID();

    @Column(unique = true)
    private String productName;

    @Column(unique = true)
    private String schema;

    @Column(unique = true)
    private String dns;
}
