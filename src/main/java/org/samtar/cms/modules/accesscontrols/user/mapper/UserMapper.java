package org.samtar.cms.modules.accesscontrols.user.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.samtar.cms.modules.accesscontrols.user.dto.CreateUserDto;
import org.samtar.cms.modules.accesscontrols.user.dto.CreateUserResponse;
import org.samtar.cms.modules.accesscontrols.user.entity.UserEntity;

@Mapper(componentModel = "spring",uses = UserProfileMapper.class)
public interface UserMapper {
    @Mapping(ignore = true, target = "statusId")
    @Mapping(ignore = true, target = "id")
    @BeanMapping(nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
    public UserEntity toEntity(CreateUserDto res);

    public CreateUserResponse tResponse(UserEntity entity);
}
