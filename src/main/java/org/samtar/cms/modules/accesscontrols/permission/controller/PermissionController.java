package org.samtar.cms.modules.accesscontrols.permission.controller;


import org.samtar.cms.modules.accesscontrols.permission.dto.PermissionReqDto;
import org.samtar.cms.modules.accesscontrols.permission.dto.PermissionResDto;
import org.samtar.cms.modules.accesscontrols.permission.service.PermissionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/permission")
public class PermissionController {

    PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("/assign")
    public PermissionResDto assignPermission(@RequestBody PermissionReqDto data) throws Exception{
return permissionService.assignPermission(data);
    }
}
