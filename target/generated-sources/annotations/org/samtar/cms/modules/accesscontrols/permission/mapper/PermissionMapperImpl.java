package org.samtar.cms.modules.accesscontrols.permission.mapper;

import javax.annotation.processing.Generated;
import org.samtar.cms.modules.accesscontrols.authority.entity.AuthorityEntity;
import org.samtar.cms.modules.accesscontrols.cmsModules.entity.CmsModuleEntity;
import org.samtar.cms.modules.accesscontrols.permission.dto.PermissionResDto;
import org.samtar.cms.modules.accesscontrols.permission.entity.PermissionEntity;
import org.samtar.cms.modules.accesscontrols.users.entity.UserEntity;
import org.samtar.cms.modules.shared.enums.Authorities;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-20T22:41:01+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class PermissionMapperImpl implements PermissionMapper {

    @Override
    public PermissionResDto toResponse(PermissionEntity Perm_entity) {
        if ( Perm_entity == null ) {
            return null;
        }

        Long userid = null;
        String authority = null;
        String module = null;

        userid = perm_entityUsersId( Perm_entity );
        Authorities authority1 = perm_entityAuthorityAuthority( Perm_entity );
        if ( authority1 != null ) {
            authority = authority1.name();
        }
        module = perm_entityCmsModuleModuleName( Perm_entity );

        PermissionResDto permissionResDto = new PermissionResDto( userid, authority, module );

        return permissionResDto;
    }

    private Long perm_entityUsersId(PermissionEntity permissionEntity) {
        UserEntity users = permissionEntity.getUsers();
        if ( users == null ) {
            return null;
        }
        return users.getId();
    }

    private Authorities perm_entityAuthorityAuthority(PermissionEntity permissionEntity) {
        AuthorityEntity authority = permissionEntity.getAuthority();
        if ( authority == null ) {
            return null;
        }
        return authority.getAuthority();
    }

    private String perm_entityCmsModuleModuleName(PermissionEntity permissionEntity) {
        CmsModuleEntity cmsModule = permissionEntity.getCmsModule();
        if ( cmsModule == null ) {
            return null;
        }
        return cmsModule.getModuleName();
    }
}
