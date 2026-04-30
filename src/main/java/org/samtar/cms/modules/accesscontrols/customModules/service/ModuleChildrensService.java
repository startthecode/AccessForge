package org.samtar.cms.modules.accesscontrols.customModules.service;

import org.samtar.cms.modules.accesscontrols.customModules.entity.ModuleChildrensEntity;
import org.samtar.cms.modules.accesscontrols.customModules.repository.ModuleChildrensRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleChildrensService {
    private final ModuleChildrensRepository moduleChildrensRepository;

    public ModuleChildrensService(ModuleChildrensRepository moduleChildrensRepository) {
        this.moduleChildrensRepository = moduleChildrensRepository;
    }


    public List<ModuleChildrensEntity> findByParentID(Long parentID){
        return moduleChildrensRepository.findByParent(parentID);
    }

}
