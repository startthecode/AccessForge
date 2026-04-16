package org.samtar.cms.modules.accesscontrols.permission.entity;

import jakarta.persistence.*;
import org.samtar.cms.modules.accesscontrols.authority.entity.AuthorityEntity;
import org.samtar.cms.modules.accesscontrols.branch.entity.BranchEntity;
import org.samtar.cms.modules.accesscontrols.department.entity.DepartmentEntity;
import org.samtar.cms.modules.accesscontrols.designation.entity.DesignationEntity;
import org.samtar.cms.modules.accesscontrols.user.entity.UserProfile;
import org.samtar.cms.modules.shared.enums.Authorities;

@Entity
@Table(name = "permissions")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "permissions_id")
    @SequenceGenerator(name = "permissions_id",sequenceName = "permissions_id",allocationSize = 5)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userProfileID")
    private UserProfile userProfile;

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
}
