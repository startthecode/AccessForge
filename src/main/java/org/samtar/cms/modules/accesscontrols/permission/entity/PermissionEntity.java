package org.samtar.cms.modules.accesscontrols.permission.entity;

import jakarta.persistence.*;
import org.samtar.cms.common.exception.CstmEntityException;
import org.samtar.cms.modules.accesscontrols.authority.entity.AuthorityEntity;
import org.samtar.cms.modules.accesscontrols.cmsModules.entity.CmsModuleEntity;
import org.samtar.cms.modules.accesscontrols.customModules.entity.ModuleChildrensEntity;
import org.samtar.cms.modules.accesscontrols.user.entity.UserEntity;
import org.samtar.cms.modules.accesscontrols.user.entity.UserProfileEntity;
import org.samtar.cms.modules.shared.enums.CmsModules;

@Entity
@Table(name = "permissions")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "permissions_id")
    @SequenceGenerator(name = "permissions_id",
            sequenceName = "permissions_id",
            allocationSize = 5)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custom_module",nullable = true)
    private ModuleChildrensEntity customModule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority",nullable = false)
    private AuthorityEntity authority;

    @ManyToOne(fetch =  FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    @JoinColumn(nullable = false)
    CmsModuleEntity cmsModule;

    @PrePersist
    @PreUpdate
    private void validateFields(){
        boolean hasUser = user != null;
        boolean hasCustomModule = customModule != null;
        if (hasUser == hasCustomModule) {
            throw CstmEntityException.EntityValidationFail("Exactly one of user or customModule must be provided");
        }
    }


    public PermissionEntity(AuthorityEntity authority,
                            ModuleChildrensEntity module,
                            UserProfileEntity userProfile,
                            UserEntity user) {
        this.authority = authority;
        this.user = user;
        this.customModule = module;
    }

    public PermissionEntity() {
    }

    public Long getId() {
        return id;
    }


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ModuleChildrensEntity getModule() {
        return customModule;
    }

    public void setModule(ModuleChildrensEntity module) {
        this.customModule = module;
    }

    public void setUserProfile(UserProfileEntity userProfileEntity) {

    }

    public ModuleChildrensEntity getDesignation() {
        return customModule;
    }

    public void setDesignation(ModuleChildrensEntity module) {

        this.customModule = module;
    }

    public AuthorityEntity getAuthority() {
        return authority;
    }

    public CmsModuleEntity getCmsModule() {
        return cmsModule;
    }

    public void setCmsModule(CmsModuleEntity cmsModule) {
        this.cmsModule = cmsModule;
    }

    public ModuleChildrensEntity getCustomModule() {
        return customModule;
    }

    public void setCustomModule(ModuleChildrensEntity customModule) {
        this.customModule = customModule;
    }

    public void setAuthority(AuthorityEntity authority) {
        this.authority = authority;
    }
}
