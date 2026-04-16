package org.samtar.cms.modules.accesscontrols.authority.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.samtar.cms.modules.shared.enums.Genders;

@Entity
@Table(name = "authority")
public class AuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "authority_id")
    @SequenceGenerator(name = "authority_id",sequenceName = "authority_id",allocationSize = 5)
    Long id;

    @NotBlank
    @Column(unique = true,nullable = false)
    String authority;
}
