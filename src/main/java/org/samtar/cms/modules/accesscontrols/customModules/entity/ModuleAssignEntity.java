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
    CustomModuleEntity module_id;

    @ManyToOne
    ModuleChildrensEntity child_id;

    @ManyToOne
    UserEntity user_id;

    public ModuleAssignEntity(ModuleChildrensEntity child_id, CustomModuleEntity module_id, UserEntity user_id) {
        this.child_id = child_id;
        this.module_id = module_id;
        this.user_id = user_id;
    }
    public ModuleAssignEntity() {

    }

    public ModuleChildrensEntity getChild_id() {
        return child_id;
    }

    public void setChild_id(ModuleChildrensEntity child_id) {
        this.child_id = child_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomModuleEntity getModule_id() {
        return module_id;
    }

    public void setModule_id(CustomModuleEntity module_id) {
        this.module_id = module_id;
    }

    public UserEntity getUser_id() {
        return user_id;
    }

    public void setUser_id(UserEntity user_id) {
        this.user_id = user_id;
    }
}
