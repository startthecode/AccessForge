package org.samtar.cms.modules.accesscontrols.user.entity;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;
import org.samtar.cms.modules.shared.entity.GenderEntity;

@Entity
@Table(name = "users_profile")
public class UserProfileEntity {

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
    @JoinColumn(name = "gender_id")
    GenderEntity genderID;

    public UserProfileEntity(String bio,

                             String lastname,
                             String name,
                             GenderEntity genderID
                           ) {
        this.bio = bio;
        this.lastname = lastname;
        this.name = name;
        this.genderID = genderID;
    }

    public UserProfileEntity() {
    }

    public GenderEntity getGenderID() {
        return genderID;
    }

    public void setGenderID(GenderEntity genderID) {
        this.genderID = genderID;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

