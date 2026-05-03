package org.samtar.cms.modules.accesscontrols.customModules.service;

import org.samtar.cms.modules.accesscontrols.customModules.service.CustomModuleService.listObj;
import org.samtar.cms.modules.accesscontrols.customModules.service.CustomModuleService.listObjWithChild;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomModuleQueryService {
    private final CustomModuleService customModuleService;
    private final ModuleChildrensService moduleChildrensService;

    public CustomModuleQueryService(CustomModuleService customModuleService,
            ModuleChildrensService moduleChildrensService) {
        this.customModuleService = customModuleService;
        this.moduleChildrensService = moduleChildrensService;
    }

    public List<listObjWithChild> getWithChild() {
        List<listObjWithChild> resp = new ArrayList<>();
        customModuleService.getAllModule().forEach(e -> resp.add(new listObjWithChild(
                e.value(),
                e.id(),
                moduleChildrensService.findByParentID(e.id())
                        .stream()
                        .map(m -> new listObj(m.getChildName(), m.getId()))
                        .toList())));
        return resp;
    }
}
