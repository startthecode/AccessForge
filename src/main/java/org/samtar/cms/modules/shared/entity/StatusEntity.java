package org.samtar.cms.modules.shared.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.samtar.cms.modules.shared.enums.Status;

@Entity
@Table(name = "status")
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "status_id")
    @SequenceGenerator(name = "status_id",sequenceName = "status_id",allocationSize = 5)
    Long id;

    @NotBlank
    @Column(unique = true,nullable = false)
            @Enumerated(EnumType.STRING)
    Status status;

    public StatusEntity(Status status) {
        this.status = status;
    }

    public StatusEntity() {
    }

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
