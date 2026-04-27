package org.samtar.cms.modules.accesscontrols.user.entity;


import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;
import org.samtar.cms.modules.shared.entity.StatusEntity;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_id")
    @SequenceGenerator(name = "user_id",sequenceName = "user_id",allocationSize = 10)
    Long id;

    @Column(nullable = false,unique = true)
    @Length(max = 25,min = 5)
    String username;

    @Column(nullable = false)
    @Length(max = 150,min = 20)
    String password;

    @Column(nullable = false)
    @Length(max = 40,min = 5)
    String email;

    Boolean accountLocked = false;

    Boolean isSuperAdmin = false;

    @ManyToOne
    @JoinColumn(name = "statusid")
    StatusEntity statusId;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "userProfile")
    UserProfileEntity userProfile;

    public UserEntity(Boolean accountLocked, String email, Boolean isSuperAdmin, String password, StatusEntity statusId, String username, UserProfileEntity userProfile) {
        this.accountLocked = accountLocked;
        this.email = email;
        this.isSuperAdmin = isSuperAdmin;
        this.password = password;
        this.statusId = statusId;
        this.username = username;
        this.userProfile = userProfile;
    }

    public UserEntity() {
    }


    public Boolean getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public Boolean getSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StatusEntity getStatusId() {
        return statusId;
    }

    public void setStatusId(StatusEntity statusId) {
        this.statusId = statusId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserProfileEntity getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfileEntity userProfile) {
        this.userProfile = userProfile;
    }
}
