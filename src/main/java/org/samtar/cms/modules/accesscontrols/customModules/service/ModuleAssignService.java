package org.samtar.cms.modules.accesscontrols.customModules.service;

import org.samtar.cms.modules.accesscontrols.customModules.repository.ModuleAssignRepository;
import org.springframework.stereotype.Service;

@Service
public class ModuleAssignService {
    private final ModuleAssignRepository moduleAssignRepository;

    public ModuleAssignService(ModuleAssignRepository moduleAssignRepository) {
        this.moduleAssignRepository = moduleAssignRepository;
    }
}
