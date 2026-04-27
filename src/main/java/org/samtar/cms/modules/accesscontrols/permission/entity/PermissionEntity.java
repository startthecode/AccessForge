package org.samtar.cms.modules.accesscontrols.permission.entity;

import jakarta.persistence.*;
import org.samtar.cms.modules.accesscontrols.authority.entity.AuthorityEntity;
import org.samtar.cms.modules.accesscontrols.branch.entity.BranchEntity;
import org.samtar.cms.modules.accesscontrols.department.entity.DepartmentEntity;
import org.samtar.cms.modules.accesscontrols.designation.entity.DesignationEntity;
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
    @JoinColumn(name = "department",nullable = true)
    private DepartmentEntity department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch",nullable = true)
    private BranchEntity branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designation",nullable = true)
    private DesignationEntity designation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority")
    private AuthorityEntity authority;

    public PermissionEntity(AuthorityEntity authority, BranchEntity branch, DepartmentEntity department, DesignationEntity designation, UserProfileEntity userProfile) {
        this.authority = authority;
        this.branch = branch;
        this.department = department;
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

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public BranchEntity getBranch() {
        return branch;
    }

    public void setBranch(BranchEntity branch) {
        this.branch = branch;
    }

    public DesignationEntity getDesignation() {
        return designation;
    }

    public void setDesignation(DesignationEntity designation) {
        this.designation = designation;
    }

    public AuthorityEntity getAuthority() {
        return authority;
    }

    public void setAuthority(AuthorityEntity authority) {
        this.authority = authority;
    }
}
