package org.samtar.cms.modules.accesscontrols.customModules.controller;

import jakarta.validation.Valid;
import org.samtar.cms.modules.accesscontrols.customModules.dto.AssignModuleReq;
import org.samtar.cms.modules.accesscontrols.customModules.service.ModuleAssignService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/module-assigns")
public class ModuleAssignController {
    private final ModuleAssignService moduleAssignService;

    public ModuleAssignController(ModuleAssignService moduleAssignService) {
        this.moduleAssignService = moduleAssignService;
    }

    @PostMapping("/add")
    public String assignModule(@Valid @RequestBody AssignModuleReq body) throws Exception{
        return moduleAssignService.assignModule(body);
    }
}
