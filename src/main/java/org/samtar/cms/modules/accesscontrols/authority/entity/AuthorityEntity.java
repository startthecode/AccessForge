package org.samtar.cms.modules.accesscontrols.authority.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.samtar.cms.modules.shared.enums.Authorities;

@Entity
@Table(name = "authority")
public class AuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_id")
    @SequenceGenerator(name = "authority_id", sequenceName = "authority_id", allocationSize = 5)
    Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    Authorities authority;

    public AuthorityEntity(Authorities authority) {
        this.authority = authority;
    }

    public AuthorityEntity() {
    }

    public Long getId() {
        return id;
    }

    public Authorities getAuthority() {
        return authority;
    }

    public void setAuthority(Authorities authority) {
        this.authority = authority;
    }
}
