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
    @Mapping(ignore = true, target = "branchID")
    @Mapping(ignore = true, target = "departmentID" )
    @Mapping(ignore = true, target = "statusId")
    @BeanMapping(nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
    public UserEntity toEntity(CreateUserDto res);

    @Mapping(target = "userProfile", source = "userProfile.branchID.name")
    @Mapping(target = "userProfile",source = "userProfile.departmentID.name")
    public CreateUserResponse tResponse(UserEntity entity);

}
