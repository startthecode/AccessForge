package org.samtar.cms.modules.accesscontrols.user.entity;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;
import org.samtar.cms.modules.accesscontrols.branch.entity.BranchEntity;
import org.samtar.cms.modules.accesscontrols.department.entity.DepartmentEntity;

@Entity
@Table(name = "usersProfile")
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

    @Column(nullable = true)
    @Length(max = 280,min = 10)
    String bio;

    @ManyToOne
    @JoinColumn(name = "branchID")
    BranchEntity branchID;

    @ManyToOne
    @JoinColumn(name = "departmentID")
    DepartmentEntity departmentID;

    public UserProfile(String bio, BranchEntity branchID, DepartmentEntity departmentID, String lastname, String name) {
        this.bio = bio;
        this.branchID = branchID;
        this.departmentID = departmentID;
        this.lastname = lastname;
        this.name = name;
    }

    public UserProfile() {
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public BranchEntity getBranchID() {
        return branchID;
    }

    public void setBranchID(BranchEntity branchID) {
        this.branchID = branchID;
    }

    public DepartmentEntity getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(DepartmentEntity departmentID) {
        this.departmentID = departmentID;
    }

    public Long getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

