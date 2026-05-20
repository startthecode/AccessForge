package org.samtar.cms.modules.accesscontrols.permission.service;

import jakarta.transaction.Transactional;
import org.samtar.cms.common.exception.PermissionException;
import org.samtar.cms.modules.accesscontrols.authority.entity.AuthorityEntity;
import org.samtar.cms.modules.accesscontrols.authority.service.AuthorityServices;
import org.samtar.cms.modules.accesscontrols.customModules.entity.ModuleChildrensEntity;
import org.samtar.cms.modules.accesscontrols.customModules.service.ModuleChildrensService;
import org.samtar.cms.modules.accesscontrols.permission.dto.PermissionReqDto;
import org.samtar.cms.modules.accesscontrols.permission.dto.PermissionResDto;
import org.samtar.cms.modules.accesscontrols.permission.entity.PermissionEntity;
import org.samtar.cms.modules.accesscontrols.permission.mapper.PermissionMapper;
import org.samtar.cms.modules.accesscontrols.permission.repository.PermissionRepository;
import org.samtar.cms.modules.accesscontrols.users.entity.UserEntity;
import org.samtar.cms.modules.accesscontrols.users.service.UserService;
import org.samtar.cms.modules.shared.enums.Authorities;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    PermissionRepository permissionRepository;
    UserService userService;
    ModuleChildrensService moduleChildrensService;
    AuthorityServices authorityServices;
    PermissionMapper permissionMapper;


    public PermissionService(PermissionRepository permissionRepository,
                             UserService userService,
                             AuthorityServices authorityServices,
                             PermissionMapper permissionMapper
    ) {
        this.permissionRepository = permissionRepository;
        this.userService = userService;
        this.authorityServices = authorityServices;
        this.permissionMapper = permissionMapper;

    }

    @Transactional
    public PermissionResDto assignPermission(PermissionReqDto reqBody) throws Exception {
        PermissionEntity assignPermission = new PermissionEntity();
        AuthorityEntity authority = authorityServices.findAuthorityOrThrow(reqBody.authorityid());
        if (reqBody.userid() != null) {
            UserEntity user = userService.getUserOrThrow(reqBody.userid(), "User not found");
            assignPermission.setUsers(user);
        } else if (reqBody.customModule() != null) {
            ModuleChildrensEntity module = moduleChildrensService.getModuleChildrensOrThrow(reqBody.customModule());
            assignPermission.setModule(module);
        }
        assignPermission.setAuthority(authority);
        return permissionMapper.toResponse(permissionRepository.save(assignPermission));
    }

    public boolean hasPermission(Authorities authorities, String moduleCode, UserEntity currentUser) throws Exception {

        if (currentUser.getSuperAdmin()) return true;
        PermissionEntity permissionsByUser = permissionRepository.findByUsers_idAndCmsModule_ModuleCode(currentUser.getId(), moduleCode).orElse(null);
        if (permissionsByUser != null) {
            return permissionsByUser.getAuthority().getAuthority() == authorities;
        } else {
            List<PermissionEntity> permissionByCmsModule = permissionRepository.
                    findByCmsModuleModuleCodeAndUsersIsNull(moduleCode);
            boolean hasPermission = false;
            permissionsByUser = permissionByCmsModule.stream()
                    .filter(e -> e.getCustomModule()
                            .getUsers()
                            .stream()
                            .anyMatch(b -> b.getId().equals(currentUser.getId())))
                    .findFirst().orElseThrow(PermissionException::accessDenied);
            return permissionsByUser.getAuthority().getAuthority() == authorities;
        }
    }
}




//@PostMapping
//@RequiresPermission(module = "USER", authority = Authorities.CREATE)
//public ResponseEntity<CreateUserResponse> create(@RequestBody CreateUserDto dto) {
//    return ResponseEntity.ok(userService.create(dto));
//}
//
//@DeleteMapping("/{id}")
//@RequiresPermission(module = "USER", authority = Authorities.DELETE)
//public ResponseEntity<Void> delete(@PathVariable Long id) {
//    userService.delete(id);
//    return ResponseEntity.noContent().build();
//}
