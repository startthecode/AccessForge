package org.samtar.cms.modules.accesscontrols.users.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.samtar.cms.modules.accesscontrols.users.dto.request.CreateUserDto;
import org.samtar.cms.modules.accesscontrols.users.dto.response.CreateUserResponse;
import org.samtar.cms.modules.accesscontrols.users.entity.UserEntity;

@Mapper(componentModel = "spring", uses = UserProfileMapper.class)
public interface UserMapper {
    @Mapping(ignore = true, target = "statusId")
    @Mapping(ignore = true, target = "password")
    @Mapping(ignore = true, target = "id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public UserEntity toEntity(CreateUserDto res);

    @Mapping(source = "userProfile", target = "profile")
    public CreateUserResponse tResponse(UserEntity entity);
}
