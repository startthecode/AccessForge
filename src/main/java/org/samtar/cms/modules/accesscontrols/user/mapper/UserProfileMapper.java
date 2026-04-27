package org.samtar.cms.modules.accesscontrols.user.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.samtar.cms.modules.accesscontrols.user.dto.UserProfileDto;
import org.samtar.cms.modules.accesscontrols.user.entity.UserProfileEntity;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    @Mapping(ignore = true, target = "branchID")
    @Mapping(ignore = true, target = "departmentID")
    @Mapping(ignore = true, target = "designationID")
    UserProfileEntity toEntity(UserProfileDto dto);

    @Mapping(ignore = true, target = "branchID")
    @Mapping(ignore = true, target = "departmentID")
    @Mapping(ignore = true, target = "designationID")
    UserProfileDto toDto(UserProfileEntity entity);
}
