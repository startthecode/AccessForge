package org.samtar.cms.modules.accesscontrols.user.mapper;
import org.mapstruct.Mapper;
import org.samtar.cms.modules.accesscontrols.user.dto.UserProfileDto;
import org.samtar.cms.modules.accesscontrols.user.entity.UserProfile;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toEntity(UserProfileDto dto);
    UserProfileDto toDto(UserProfile entity);
}
