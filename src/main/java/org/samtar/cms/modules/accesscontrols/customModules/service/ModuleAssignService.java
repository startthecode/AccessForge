package org.samtar.cms.modules.accesscontrols.customModules.service;

import org.samtar.cms.modules.accesscontrols.customModules.dto.AssignModuleReq;
import org.samtar.cms.modules.accesscontrols.customModules.entity.CustomModuleEntity;
import org.samtar.cms.modules.accesscontrols.customModules.entity.ModuleAssignEntity;
import org.samtar.cms.modules.accesscontrols.customModules.entity.ModuleChildrensEntity;
import org.samtar.cms.modules.accesscontrols.customModules.repository.ModuleAssignRepository;
import org.samtar.cms.modules.accesscontrols.user.entity.UserEntity;
import org.samtar.cms.modules.accesscontrols.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class ModuleAssignService {
    private final ModuleAssignRepository moduleAssignRepository;
    private final UserService userService;
    private final ModuleChildrensService moduleChildrensService;

    public ModuleAssignService(ModuleAssignRepository moduleAssignRepository, UserService userService,
            ModuleChildrensService moduleChildrensService) {
        this.moduleAssignRepository = moduleAssignRepository;
        this.userService = userService;
        this.moduleChildrensService = moduleChildrensService;
    }

    public String assignModule(AssignModuleReq body) throws Exception {

        ModuleAssignEntity moduleAssign = new ModuleAssignEntity();
        UserEntity user = userService.getUserOrThrow(body.userid(), "User does not exists");
        ModuleChildrensEntity child = moduleChildrensService.getModuleChildrensOrThrow(body.moduleid());
        CustomModuleEntity module = child.getParent();
        moduleAssign.setUserId(user);
        moduleAssign.setModuleId(module);
        moduleAssign.setChildId(child);
        moduleAssignRepository.save(moduleAssign);

        return "Module assigned successfully";
    }

}
