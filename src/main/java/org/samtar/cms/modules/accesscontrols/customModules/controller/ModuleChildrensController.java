package org.samtar.cms.modules.accesscontrols.customModules.controller;

import org.samtar.cms.modules.accesscontrols.customModules.dto.AddUserToChildReq;
import org.samtar.cms.modules.accesscontrols.customModules.dto.CreateCldModReq;
import org.samtar.cms.modules.accesscontrols.customModules.service.ModuleChildrensService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/module-childrens")
public class ModuleChildrensController {
    private final ModuleChildrensService moduleChildrensService;

    public ModuleChildrensController(ModuleChildrensService moduleChildrensService) {
        this.moduleChildrensService = moduleChildrensService;
    }

    @PostMapping("/create")
    public String create(@Valid @RequestBody CreateCldModReq body) {
        return moduleChildrensService.create(body);
    }
    @PostMapping("/adduser-to-module")
    public  String addUserToModule(@RequestBody AddUserToChildReq data) throws Exception{
        return moduleChildrensService.addUserToModule(data);
    }
}
