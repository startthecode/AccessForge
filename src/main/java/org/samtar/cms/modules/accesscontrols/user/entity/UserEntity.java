package org.samtar.cms.modules.accesscontrols.user.entity;


import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;
import org.samtar.cms.modules.shared.entity.StatusEntity;

@Entity
@Table(name = "user")
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
    UserProfile userProfile;
}
