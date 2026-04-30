package org.samtar.cms.modules.accesscontrols.customModules.service;

import org.samtar.cms.common.exception.CustomModuleException;
import org.samtar.cms.modules.accesscontrols.customModules.entity.CustomModuleEntity;
import org.samtar.cms.modules.accesscontrols.customModules.entity.ModuleChildrensEntity;
import org.samtar.cms.modules.accesscontrols.customModules.repository.CustomModuleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomModuleService {
    public record listObj(String value, Long id) {
    }

    public record listObjWithChild(String value, Long id, List<listObj> child) {
    }

    CustomModuleRepository customModuleRepository;
    ModuleChildrensService moduleChildrensService;

    public CustomModuleService(CustomModuleRepository customModuleRepository, ModuleChildrensService moduleChildrensService) {
        this.customModuleRepository = customModuleRepository;
        this.moduleChildrensService = moduleChildrensService;
    }

    public String createModule(String moduleName) {
        CustomModuleEntity newModule = new CustomModuleEntity();
        newModule.setHas_child(false);
        newModule.setModule_name(moduleName);
        customModuleRepository.save(newModule);
        return moduleName + " Module has been created";
    }

    public String deleteModule(long id) {
        CustomModuleEntity module = getModuleOrThrow(id);
        customModuleRepository.delete(module);
        return module.getModule_name() + " Module has been deleted";
    }

    public String updateModule(long id, String newName) {
        CustomModuleEntity module = getModuleOrThrow(id);
        module.setModule_name(newName);
        return customModuleRepository.save(module).getModule_name() + " Module has been deleted";
    }

    public List<listObj> getAllModule() {
        List<listObj> resp = new ArrayList<>();
        customModuleRepository.findAll().forEach((e) -> {
            resp.add(new listObj(e.getModule_name(), e.getId()));
        });
        return resp;
    }

    public CustomModuleEntity getModuleOrThrow(long id) {
        return customModuleRepository.findById(id).orElseThrow(CustomModuleException::moduleNotFound);
    }

    // children
    public List<listObjWithChild> getWithChild() {
        List<listObjWithChild> resp = new ArrayList<>();
        this.getAllModule().forEach(e -> {
            resp.add(new listObjWithChild(e
                    .value(),
                    e.id,
                    moduleChildrensService
                            .findByParentID(e.id)
                            .stream()
                            .map((m) -> new listObj(m.getChildName(), m.getId())).toList()));
        });
        return resp;
    }


}
