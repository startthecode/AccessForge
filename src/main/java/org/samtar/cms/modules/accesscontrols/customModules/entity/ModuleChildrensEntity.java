package org.samtar.cms.modules.accesscontrols.customModules.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.samtar.cms.modules.accesscontrols.user.entity.UserEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "module_childrens")
public class ModuleChildrensEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "children_modules")
    @SequenceGenerator(name = "children_modules",sequenceName = "children_modules",allocationSize = 5)
    Long id;

    @NotBlank
    @Column(nullable = false)
    String childName;

    @ManyToOne
    CustomModuleEntity parent;

    @ManyToOne
    UserEntity userId;

    @CreationTimestamp
    LocalDateTime createdAt;

    public ModuleChildrensEntity(String childName, LocalDateTime createdAt, Long id, CustomModuleEntity parent, UserEntity userId) {
        this.childName = childName;
        this.createdAt = createdAt;
        this.id = id;
        this.parent = parent;
        this.userId = userId;
    }
    public ModuleChildrensEntity(){
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }



    public CustomModuleEntity getParent() {
        return parent;
    }

    public void setParent(CustomModuleEntity parent) {
        this.parent = parent;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }
}
