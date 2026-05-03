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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_modules_id")
    @SequenceGenerator(name = "custom_modules_id", sequenceName = "custom_modules_id", allocationSize = 5)
    Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    String moduleName;

    @NotNull
    @Column(nullable = false)
    boolean hasChild;

    @CreationTimestamp
    LocalDateTime createdAt;

    @ManyToOne
    UserEntity createdBy;

    public CustomModuleEntity(boolean hasChild, String moduleName) {
        this.hasChild = hasChild;
        this.moduleName = moduleName;
    }

    public CustomModuleEntity() {
    }

    public UserEntity getcreatedBy() {
        return createdBy;
    }

    public void setcreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
