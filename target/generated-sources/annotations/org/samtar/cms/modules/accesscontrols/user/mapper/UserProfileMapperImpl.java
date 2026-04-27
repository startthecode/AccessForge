package org.samtar.cms.modules.accesscontrols.user.mapper;

import javax.annotation.processing.Generated;
import org.samtar.cms.modules.accesscontrols.user.dto.UserProfileDto;
import org.samtar.cms.modules.accesscontrols.user.entity.UserProfileEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-27T23:49:50+0530",
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
    public UserProfileDto toDto(UserProfileEntity entity) {
        if ( entity == null ) {
            return null;
        }

        String name = null;
        String bio = null;
        String lastname = null;

        name = entity.getName();
        bio = entity.getBio();
        lastname = entity.getLastname();

        Long branchID = null;
        Long departmentID = null;
        Long designationID = null;
        Long gender = null;

        UserProfileDto userProfileDto = new UserProfileDto( name, bio, lastname, gender, branchID, departmentID, designationID );

        return userProfileDto;
    }
}
