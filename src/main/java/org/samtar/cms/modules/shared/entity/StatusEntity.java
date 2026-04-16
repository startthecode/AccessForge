package org.samtar.cms.modules.shared.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "status")
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "status_id")
    @SequenceGenerator(name = "status_id",sequenceName = "status_id",allocationSize = 5)
    Long id;

    @NotBlank
    @Column(unique = true,nullable = false)
    String status;
}
