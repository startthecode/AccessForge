package org.samtar.cms.security.interceptor;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.samtar.cms.modules.accesscontrols.permission.service.PermissionService;
import org.samtar.cms.modules.accesscontrols.user.entity.UserEntity;
import org.samtar.cms.modules.accesscontrols.user.service.UserService;
import org.samtar.cms.security.annotation.RequiresPermission;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PermissionInterceptor {
    PermissionService permissionService;
    UserService userService;
    public PermissionInterceptor(PermissionService permissionService,
                                 UserService userService) {
        this.permissionService = permissionService;
        this.userService = userService;
    }

    @Before("@anotation(requires)")
    public void checkPermission(RequiresPermission requiresPermissions) throws Exception{
        UserEntity currentUser = userService.getCurrentUserEntity();
        permissionService.hasPermission(requiresPermissions.authority(),requiresPermissions.moduleCode(),currentUser);
    }
}
