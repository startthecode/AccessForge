package org.samtar.cms.modules.accesscontrols.customModules.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.samtar.cms.modules.accesscontrols.user.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "module_childrens")
public class ModuleChildrensEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "children_modules")
    @SequenceGenerator(
            name = "children_modules",
            sequenceName = "children_modules",
            allocationSize = 5
    )
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String childName;

    @ManyToOne
    private CustomModuleEntity parent;

    @ManyToMany
    @JoinTable(
            name = "module_children_users",
            joinColumns = @JoinColumn(name = "module_children_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> users = new ArrayList<>();;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public ModuleChildrensEntity() {
    }

    public ModuleChildrensEntity(
            String childName,
            LocalDateTime createdAt,
            Long id,
            CustomModuleEntity parent,
            List<UserEntity> users
    ) {
        this.childName = childName;
        this.createdAt = createdAt;
        this.id = id;
        this.parent = parent;
        this.users = users;
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

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
