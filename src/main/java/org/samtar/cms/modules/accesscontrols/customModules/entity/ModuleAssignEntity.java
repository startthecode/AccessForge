package org.samtar.cms.modules.accesscontrols.customModules.entity;


import jakarta.persistence.*;
import org.samtar.cms.modules.accesscontrols.user.entity.UserEntity;

@Entity
@Table(name = "module_Assign")
public class ModuleAssignEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "children_modules")
    @SequenceGenerator(name = "children_modules",sequenceName = "children_modules",allocationSize = 5)
    Long id;

    @ManyToOne
    CustomModuleEntity moduleId;

    @ManyToOne
    ModuleChildrensEntity childId;

    @ManyToOne
    UserEntity userId;

    public ModuleAssignEntity(ModuleChildrensEntity childId, CustomModuleEntity moduleId, UserEntity userId) {
        this.childId = childId;
        this.moduleId = moduleId;
        this.userId = userId;
    }
    public ModuleAssignEntity() {

    }

    public ModuleChildrensEntity getChildId() {
        return childId;
    }

    public void setChildId(ModuleChildrensEntity childId) {
        this.childId = childId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomModuleEntity getModuleId() {
        return moduleId;
    }

    public void setModuleId(CustomModuleEntity moduleId) {
        this.moduleId = moduleId;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }
}
