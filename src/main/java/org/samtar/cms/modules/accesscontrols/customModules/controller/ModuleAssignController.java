package org.samtar.cms.modules.accesscontrols.customModules.controller;

import org.samtar.cms.modules.accesscontrols.customModules.service.ModuleAssignService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/module-assigns")
public class ModuleAssignController {
    private final ModuleAssignService moduleAssignService;

    public ModuleAssignController(ModuleAssignService moduleAssignService) {
        this.moduleAssignService = moduleAssignService;
    }
}
