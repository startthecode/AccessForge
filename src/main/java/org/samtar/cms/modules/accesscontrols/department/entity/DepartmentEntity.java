package org.samtar.cms.modules.accesscontrols.department.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.samtar.cms.modules.shared.enums.Genders;

@Entity
@Table(name = "department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "department_id")
    @SequenceGenerator(name = "department_id",sequenceName = "department_id",allocationSize = 5)
    Long id;

    @NotBlank
    @Column(unique = true,nullable = false)
    @Enumerated(EnumType.STRING)
    Genders department;
}

