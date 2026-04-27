package org.samtar.cms.modules.accesscontrols.department.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_id")
    @SequenceGenerator(name = "department_id", sequenceName = "department_id", allocationSize = 5)
    Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    String department;

    public DepartmentEntity(String department) {
        this.department = department;
    }

    public DepartmentEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
