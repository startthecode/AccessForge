package org.samtar.cms.modules.accesscontrols.customModules.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.samtar.cms.modules.accesscontrols.user.entity.UserEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "custom_modules")
public class CustomModuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customModules_id")
    @SequenceGenerator(name = "customModules_id",sequenceName = "customModules_id",allocationSize = 5)
    Long id;

    @NotBlank
    @Column(unique = true,nullable = false)
    String module_name;

    @NotNull
    @Column(nullable = false)
    boolean has_child;

    @CreationTimestamp
    LocalDateTime createdAt;

    @ManyToOne
    UserEntity created_by;

    public CustomModuleEntity( boolean has_child, String module_name) {
        this.has_child = has_child;
        this.module_name = module_name;
    }
    public CustomModuleEntity() {
      }

    public UserEntity getCreated_by() {
        return created_by;
    }

    public void setCreated_by(UserEntity created_by) {
        this.created_by = created_by;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isHas_child() {
        return has_child;
    }

    public void setHas_child(boolean has_child) {
        this.has_child = has_child;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }
}
