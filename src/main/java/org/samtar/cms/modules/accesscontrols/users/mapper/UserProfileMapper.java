package org.samtar.cms.modules.accesscontrols.users.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.samtar.cms.modules.accesscontrols.users.dto.request.UserProfileDto;
import org.samtar.cms.modules.accesscontrols.users.dto.response.UserProfileResponseDto;
import org.samtar.cms.modules.accesscontrols.users.entity.UserProfileEntity;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    @Mapping(ignore = true, target = "genderID")
    UserProfileEntity toEntity(UserProfileDto dto);

    @Mapping(source = "genderID.gender",target = "genderID")
    UserProfileResponseDto toDto(UserProfileEntity entity);
}
