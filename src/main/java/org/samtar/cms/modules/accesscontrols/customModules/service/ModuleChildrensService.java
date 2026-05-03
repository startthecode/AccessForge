package org.samtar.cms.modules.accesscontrols.customModules.service;

import org.samtar.cms.common.exception.CustomModuleException;
import org.samtar.cms.modules.accesscontrols.customModules.dto.CreateCldModReq;
import org.samtar.cms.modules.accesscontrols.customModules.entity.CustomModuleEntity;
import org.samtar.cms.modules.accesscontrols.customModules.entity.ModuleChildrensEntity;
import org.samtar.cms.modules.accesscontrols.customModules.repository.ModuleChildrensRepository;
import org.samtar.cms.modules.accesscontrols.user.entity.UserEntity;
import org.samtar.cms.modules.accesscontrols.user.service.UserService;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleChildrensService {
    private final ModuleChildrensRepository moduleChildrensRepository;
    private final UserService userService;
    CustomModuleService customModuleService;
    public ModuleChildrensService(ModuleChildrensRepository moduleChildrensRepository,
                                  UserService userService,
                                  CustomModuleService customModuleService) {
        this.moduleChildrensRepository = moduleChildrensRepository;
        this.userService = userService;
        this.customModuleService = customModuleService;
    }

    public List<ModuleChildrensEntity> findByParentID(Long parentID) {
        return moduleChildrensRepository.findByParent_id(parentID);
    }

    public String create(CreateCldModReq body) {
        UserEntity currentUser = userService.getCurrentUserEntity();
        CustomModuleEntity parent = customModuleService.getModuleOrThrow(body.parentid());
        ModuleChildrensEntity moduleChildrensEntity = new ModuleChildrensEntity();
        moduleChildrensEntity.setChildName(body.childname());
        moduleChildrensEntity.setParent(parent);
        moduleChildrensEntity.setUserId(currentUser);
        return moduleChildrensRepository.save(moduleChildrensEntity).getChildName();
    }

    public String delete(Long id) {
        moduleChildrensRepository.deleteById(id);
        return "Module children deleted successfully";
    }

    public String update(Long id, String name) {
        ModuleChildrensEntity moduleChildrensEntity = moduleChildrensRepository.findById(id).get();
        moduleChildrensEntity.setChildName(name);
        moduleChildrensRepository.save(moduleChildrensEntity);
        return "Module children updated successfully";
    }

    public String getById(Long id) {
        return moduleChildrensRepository.findById(id).get().getChildName();
    }

    public ModuleChildrensEntity getModuleChildrensOrThrow(Long id) {
        return moduleChildrensRepository.findById(id).orElseThrow(CustomModuleException::moduleChildrensNotFound);
    }
}
