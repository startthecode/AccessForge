package org.samtar.cms.modules.accesscontrols.authority.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

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

    public AuthorityEntity(String authority) {
        this.authority = authority;
    }

    public AuthorityEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
