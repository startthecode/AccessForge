package org.samtar.cms.modules.accesscontrols.customModules.controller;

import org.samtar.cms.modules.accesscontrols.customModules.service.ModuleChildrensService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/module-childrens")
public class ModuleChildrensController {
    private final ModuleChildrensService moduleChildrensService;

    public ModuleChildrensController(ModuleChildrensService moduleChildrensService) {
        this.moduleChildrensService = moduleChildrensService;
    }
}
