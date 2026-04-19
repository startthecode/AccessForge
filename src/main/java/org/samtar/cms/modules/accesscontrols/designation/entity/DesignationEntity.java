package org.samtar.cms.modules.accesscontrols.designation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "designation")
public class DesignationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "designation_id")
    @SequenceGenerator(name = "designation_id",sequenceName = "designation_id",allocationSize = 5)
    Long id;

    @NotBlank
    @Column(unique = true,nullable = false)
    String designation;

    public DesignationEntity(String designation) {
        this.designation = designation;
    }

    public DesignationEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
