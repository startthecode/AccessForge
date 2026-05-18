package org.samtar.cms.modules.accesscontrols.customModules.service;

import org.samtar.cms.common.exception.CustomModuleException;
import org.samtar.cms.modules.accesscontrols.customModules.dto.AddUserToChildReq;
import org.samtar.cms.modules.accesscontrols.customModules.dto.CreateCldModReq;
import org.samtar.cms.modules.accesscontrols.customModules.entity.CustomModuleEntity;
import org.samtar.cms.modules.accesscontrols.customModules.entity.ModuleChildrensEntity;
import org.samtar.cms.modules.accesscontrols.customModules.repository.ModuleChildrensRepository;
import org.samtar.cms.modules.accesscontrols.users.entity.UserEntity;
import org.samtar.cms.modules.accesscontrols.users.service.UserService;
import org.samtar.cms.modules.shared.enums.Authorities;
import org.samtar.cms.security.annotation.RequiresPermission;
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
        System.out.println(body.parentid());
        System.out.println(body.childname());
        UserEntity currentUser = userService.getCurrentUserEntity();
        CustomModuleEntity parent = customModuleService.getModuleOrThrow(body.parentid());
        ModuleChildrensEntity moduleChildrensEntity = new ModuleChildrensEntity();
        moduleChildrensEntity.setChildName(body.childname());
        moduleChildrensEntity.setParent(parent);
//        moduleChildrensEntity.getUsers().add(currentUser);
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

    @RequiresPermission(
            moduleCode = "CUSTOM_MODULES",
            authority = Authorities.ALL
    )
    public String addUserToModule(AddUserToChildReq data) throws Exception {
        ModuleChildrensEntity module = moduleChildrensRepository.findById(data.childModuleID()).orElseThrow(CustomModuleException::moduleChildrensNotFound);
        UserEntity userToBeAdd = userService.getUserOrThrow(data.userID(),"User not found");
        module.getUsers().add(userToBeAdd);
        moduleChildrensRepository.save(module);
        return userToBeAdd.getUsername() + " added to the module " + module.getChildName();
    }
    public String getById(Long id) {
        return moduleChildrensRepository.findById(id).get().getChildName();
    }

    public ModuleChildrensEntity getModuleChildrensOrThrow(Long id) {
        return moduleChildrensRepository.findById(id).orElseThrow(CustomModuleException::moduleChildrensNotFound);
    }


}
