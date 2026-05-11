package org.samtar.cms.modules.accesscontrols.permission.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.samtar.cms.modules.accesscontrols.permission.dto.PermissionResDto;
import org.samtar.cms.modules.accesscontrols.permission.entity.PermissionEntity;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    @Mapping(target = "authority",source = "authority.childName")
    @Mapping(target = "module",source = "customModule.authority")
    PermissionResDto toResponse(PermissionEntity Perm_entity);

}
