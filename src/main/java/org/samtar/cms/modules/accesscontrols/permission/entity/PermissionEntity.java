package org.samtar.cms.modules.accesscontrols.permission.entity;

import jakarta.persistence.*;
import org.samtar.cms.modules.accesscontrols.authority.entity.AuthorityEntity;
import org.samtar.cms.modules.accesscontrols.customModules.entity.CustomModuleEntity;
import org.samtar.cms.modules.accesscontrols.user.entity.UserProfileEntity;

@Entity
@Table(name = "permissions")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "permissions_id")
    @SequenceGenerator(name = "permissions_id",sequenceName = "permissions_id",allocationSize = 5)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userProfileID")
    private UserProfileEntity userProfile;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designation",nullable = true)
    private CustomModuleEntity designation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority")
    private AuthorityEntity authority;

    public PermissionEntity(AuthorityEntity authority,  CustomModuleEntity designation, UserProfileEntity userProfile) {
        this.authority = authority;

        this.designation = designation;
        this.userProfile = userProfile;
    }

    public PermissionEntity() {
    }

    public Long getId() {
        return id;
    }

    public UserProfileEntity getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfileEntity userProfileEntity) {
        this.userProfile = userProfileEntity;
    }





    public CustomModuleEntity getDesignation() {
        return designation;
    }

    public void setDesignation(CustomModuleEntity designation) {
        this.designation = designation;
    }

    public AuthorityEntity getAuthority() {
        return authority;
    }

    public void setAuthority(AuthorityEntity authority) {
        this.authority = authority;
    }
}
