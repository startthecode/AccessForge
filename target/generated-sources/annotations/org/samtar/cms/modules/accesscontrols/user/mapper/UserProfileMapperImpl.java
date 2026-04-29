package org.samtar.cms.modules.accesscontrols.user.mapper;

import javax.annotation.processing.Generated;
import org.samtar.cms.modules.accesscontrols.branch.entity.BranchEntity;
import org.samtar.cms.modules.accesscontrols.department.entity.DepartmentEntity;
import org.samtar.cms.modules.accesscontrols.designation.entity.DesignationEntity;
import org.samtar.cms.modules.accesscontrols.user.dto.request.UserProfileDto;
import org.samtar.cms.modules.accesscontrols.user.dto.response.UserProfileResponseDto;
import org.samtar.cms.modules.accesscontrols.user.entity.UserProfileEntity;
import org.samtar.cms.modules.shared.entity.GenderEntity;
import org.samtar.cms.modules.shared.enums.Genders;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-29T23:33:41+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
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

        String branchID = null;
        String departmentID = null;
        String designationID = null;
        String genderID = null;
        String name = null;
        String bio = null;
        String lastname = null;

        branchID = entityBranchIDBranch( entity );
        departmentID = entityDepartmentIDDepartment( entity );
        designationID = entityDesignationIDDesignation( entity );
        Genders gender = entityGenderIDGender( entity );
        if ( gender != null ) {
            genderID = gender.name();
        }
        name = entity.getName();
        bio = entity.getBio();
        lastname = entity.getLastname();

        UserProfileResponseDto userProfileResponseDto = new UserProfileResponseDto( name, bio, lastname, genderID, branchID, departmentID, designationID );

        return userProfileResponseDto;
    }

    private String entityBranchIDBranch(UserProfileEntity userProfileEntity) {
        BranchEntity branchID = userProfileEntity.getBranchID();
        if ( branchID == null ) {
            return null;
        }
        return branchID.getBranch();
    }

    private String entityDepartmentIDDepartment(UserProfileEntity userProfileEntity) {
        DepartmentEntity departmentID = userProfileEntity.getDepartmentID();
        if ( departmentID == null ) {
            return null;
        }
        return departmentID.getDepartment();
    }

    private String entityDesignationIDDesignation(UserProfileEntity userProfileEntity) {
        DesignationEntity designationID = userProfileEntity.getDesignationID();
        if ( designationID == null ) {
            return null;
        }
        return designationID.getDesignation();
    }

    private Genders entityGenderIDGender(UserProfileEntity userProfileEntity) {
        GenderEntity genderID = userProfileEntity.getGenderID();
        if ( genderID == null ) {
            return null;
        }
        return genderID.getGender();
    }
}
