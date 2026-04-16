package org.samtar.cms.modules.shared.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.samtar.cms.modules.shared.enums.Genders;


@Entity
@Table(name = "gender")

public class GenderEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "gender_ID")
        @SequenceGenerator(name = "gender_ID",sequenceName = "gender_ID",allocationSize = 5)
        Long id;

        @NotBlank
        @Column(unique = true,nullable = false)
        @Enumerated(EnumType.STRING)
        Genders gender;

}
