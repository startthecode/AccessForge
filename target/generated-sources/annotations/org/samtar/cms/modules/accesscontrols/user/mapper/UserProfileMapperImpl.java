package org.samtar.cms.modules.accesscontrols.user.mapper;

import javax.annotation.processing.Generated;
import org.samtar.cms.modules.accesscontrols.user.dto.request.UserProfileDto;
import org.samtar.cms.modules.accesscontrols.user.dto.response.UserProfileResponseDto;
import org.samtar.cms.modules.accesscontrols.user.entity.UserProfileEntity;
import org.samtar.cms.modules.shared.entity.GenderEntity;
import org.samtar.cms.modules.shared.enums.Genders;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-02T16:52:05+0530",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class UserProfileMapperImpl implements UserProfileMapper {

    @Override
    public UserProfileEntity toEntity(UserProfileDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserProfileEntity userProfileEntity = new UserProfileEntity();

        userProfileEntity.setBio( dto.bio() );
        userProfileEntity.setLastname( dto.lastname() );
        userProfileEntity.setName( dto.name() );

        return userProfileEntity;
    }

    @Override
    public UserProfileResponseDto toDto(UserProfileEntity entity) {
        if ( entity == null ) {
            return null;
        }

        String genderID = null;
        String name = null;
        String bio = null;
        String lastname = null;

        Genders gender = entityGenderIDGender( entity );
        if ( gender != null ) {
            genderID = gender.name();
        }
        name = entity.getName();
        bio = entity.getBio();
        lastname = entity.getLastname();

        String branchID = null;
        String departmentID = null;
        String designationID = null;

        UserProfileResponseDto userProfileResponseDto = new UserProfileResponseDto( name, bio, lastname, genderID, branchID, departmentID, designationID );

        return userProfileResponseDto;
    }

    private Genders entityGenderIDGender(UserProfileEntity userProfileEntity) {
        GenderEntity genderID = userProfileEntity.getGenderID();
        if ( genderID == null ) {
            return null;
        }
        return genderID.getGender();
    }
}
