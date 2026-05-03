package org.samtar.cms.modules.accesscontrols.customModules.controller;

import java.util.List;

import org.samtar.cms.modules.accesscontrols.customModules.service.CustomModuleQueryService;
import org.samtar.cms.modules.accesscontrols.customModules.service.CustomModuleService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/custom-modules")
public class CustomModuleController {
    private final CustomModuleService customModuleService;
    private final CustomModuleQueryService customModuleQueryService;

    public CustomModuleController(CustomModuleService customModuleService,
                                  CustomModuleQueryService customModuleQueryService) {
        this.customModuleService = customModuleService;
        this.customModuleQueryService = customModuleQueryService;
    }

    @GetMapping("/all/withchild")
    public List<CustomModuleService.listObjWithChild> getWithChild() {
        return customModuleQueryService.getWithChild();
    }

    @GetMapping("/all")
    public List<CustomModuleService.listObj> getAll() {
        return customModuleService.getAllModule();
    }

    @PostMapping("/create")
    public String create(@RequestBody String name) {
        return customModuleService.createModule(name);
    }
    
    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody String name) {
        return customModuleService.updateModule(id, name);
    }
    
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        customModuleService.deleteModule(id);
    }
    

}
