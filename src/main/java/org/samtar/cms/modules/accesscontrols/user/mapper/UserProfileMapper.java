package org.samtar.cms.modules.accesscontrols.user.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.samtar.cms.modules.accesscontrols.user.dto.request.UserProfileDto;
import org.samtar.cms.modules.accesscontrols.user.dto.response.UserProfileResponseDto;
import org.samtar.cms.modules.accesscontrols.user.entity.UserProfileEntity;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    @Mapping(ignore = true, target = "genderID")
    UserProfileEntity toEntity(UserProfileDto dto);

    @Mapping(source = "genderID.gender",target = "genderID")
    UserProfileResponseDto toDto(UserProfileEntity entity);
}
