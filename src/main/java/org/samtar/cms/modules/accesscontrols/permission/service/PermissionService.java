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
import org.samtar.cms.modules.accesscontrols.user.entity.UserEntity;
import org.samtar.cms.modules.accesscontrols.user.service.UserService;
import org.samtar.cms.modules.shared.enums.Authorities;
import org.samtar.cms.modules.shared.enums.CmsModules;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public PermissionResDto createPermission(PermissionReqDto reqBody) throws Exception {
        PermissionEntity assignPermission = new PermissionEntity();
        AuthorityEntity authority = authorityServices.findAuthorityOrThrow(reqBody.authorityid());
        if (reqBody.userid() != null) {
            UserEntity user = userService.getUserOrThrow(reqBody.userid(), "User not found");
            assignPermission.setUser(user);
        } else if (reqBody.customModule() != null) {
            ModuleChildrensEntity module = moduleChildrensService.getModuleChildrensOrThrow(reqBody.customModule());
            assignPermission.setModule(module);
        }
        assignPermission.setAuthority(authority);
        return permissionMapper.toResponse(permissionRepository.save(assignPermission));
    }

    public boolean hasPermission(Authorities authorities, String moduleCode, UserEntity currentUser) throws Exception {

        if (currentUser.getSuperAdmin()) return true;
        PermissionEntity permissionsByUser = permissionRepository.findByUserAndCmsModuleModuleCode(currentUser.getId(), moduleCode).orElse(null);
        if (permissionsByUser != null) {
            return permissionsByUser.getAuthority().getAuthority() == authorities;
        } else {
            List<PermissionEntity> permissionByCmsModule = permissionRepository.
                    findByCmsModuleModuleCodeAndUseridIsNull(moduleCode);
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


//package org.samtar.cms.security.aspect;
//
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.samtar.cms.modules.accesscontrols.user.service.imps.UserPrincipleImps;
//import org.samtar.cms.security.annotation.RequiresPermission;
//import org.samtar.cms.security.exception.AccessDeniedException;
//import org.samtar.cms.security.resolver.PermissionService;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class PermissionAspect {
//
//    private final PermissionService permissionService;
//
//    public PermissionAspect(PermissionService permissionService) {
//        this.permissionService = permissionService;
//    }
//
//    @Before("@annotation(requires)")
//    public void check(RequiresPermission requires) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth == null || !(auth.getPrincipal() instanceof UserPrincipleImps principal)) {
//            throw new AccessDeniedException("Not authenticated");
//        }
//        boolean ok = permissionService.hasPermission(
//                principal.getUser(), requires.module(), requires.authority());
//        if (!ok) {
//            throw new AccessDeniedException(
//                    "Missing " + requires.authority() + " on " + requires.module());
//        }
//    }
//}


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
