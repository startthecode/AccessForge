package org.samtar.cms.modules.accesscontrols.customModules.service;

import org.samtar.cms.common.exception.CustomModuleException;
import org.samtar.cms.modules.accesscontrols.customModules.entity.CustomModuleEntity;
import org.samtar.cms.modules.accesscontrols.customModules.repository.CustomModuleRepository;
import org.samtar.cms.modules.accesscontrols.user.entity.UserEntity;
import org.samtar.cms.modules.accesscontrols.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomModuleService {
    public record listObj(String value, Long id) {
    }

    public record listObjWithChild(String value, Long id, List<listObj> child) {
    }

    CustomModuleRepository customModuleRepository;
    UserService userService;

    public CustomModuleService(CustomModuleRepository customModuleRepository, UserService userService) {
        this.customModuleRepository = customModuleRepository;
        this.userService = userService;
    }

    public String createModule(String moduleName) {
        UserEntity currentUser = userService.getCurrentUserEntity();
        CustomModuleEntity newModule = new CustomModuleEntity();
        newModule.setHasChild(false);
        newModule.setModuleName(moduleName);
        newModule.setcreatedBy(currentUser);
        customModuleRepository.save(newModule);
        return newModule.getModuleName() + " Module has been created";
    }

    public String deleteModule(long id) {
        CustomModuleEntity module = getModuleOrThrow(id);
        customModuleRepository.delete(module);
        return module.getModuleName() + " Module has been deleted";
    }

    public String updateModule(long id, String newName) {
        CustomModuleEntity module = getModuleOrThrow(id);
        module.setModuleName(newName);
        return customModuleRepository.save(module).getModuleName() + " Module has been deleted";
    }

    public List<listObj> getAllModule() {
        List<listObj> resp = new ArrayList<>();
        customModuleRepository.findAll().forEach((e) -> {
            resp.add(new listObj(e.getModuleName(), e.getId()));
        });
        return resp;
    }

    public CustomModuleEntity getModuleOrThrow(long id) {
        return customModuleRepository.findById(id).orElseThrow(CustomModuleException::moduleNotFound);
    }

}
