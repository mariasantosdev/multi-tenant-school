package com.multi.tenant.school.tenant.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tenant")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Builder
@Setter
//TODO talvez adicionar outros atributos aqui que hoje estao no envie
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String productName;

    @Column(name="schema_name", unique = true)
    private String schema;

    @Column(unique = true)
    private String dns;
}
