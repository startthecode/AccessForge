package org.samtar.cms.modules.accesscontrols.designation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.samtar.cms.modules.shared.enums.Genders;

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
}
