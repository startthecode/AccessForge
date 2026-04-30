package org.samtar.cms.modules.accesscontrols.customModules.controller;

import org.samtar.cms.modules.accesscontrols.customModules.service.CustomModuleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/custom-modules")
public class CustomModuleController {
    private final CustomModuleService customModuleService;

    public CustomModuleController(CustomModuleService customModuleService) {
        this.customModuleService = customModuleService;
    }
}
