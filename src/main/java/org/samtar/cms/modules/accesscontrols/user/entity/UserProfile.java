package org.samtar.cms.modules.accesscontrols.user.entity;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;
import org.samtar.cms.modules.accesscontrols.branch.entity.BranchEntity;
import org.samtar.cms.modules.accesscontrols.department.entity.DepartmentEntity;
import org.samtar.cms.modules.shared.entity.StatusEntity;



    @Entity
    @Table(name = "user")
public class UserProfile {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_profile")
        @SequenceGenerator(name = "user_profile",sequenceName = "user_profile",allocationSize = 10)
        Long id;

        @Column(nullable = false)
        @Length(max = 25,min = 3)
        String name;

        @Column(nullable = true)
        @Length(max = 25,min = 3)
        String lastname;

        @Column(nullable = false)
        @Length(max = 280,min = 10)
        String bio;

        @ManyToOne
        @JoinColumn(name = "branchID")
        BranchEntity branchID;

        @ManyToOne
        @JoinColumn(name = "departmentID")
        DepartmentEntity departmentID;

    }

