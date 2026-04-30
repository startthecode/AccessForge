package org.samtar.cms.modules.accesscontrols.customModules.repository;

import org.samtar.cms.modules.accesscontrols.customModules.entity.ModuleChildrensEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleChildrensRepository extends JpaRepository<ModuleChildrensEntity,Long> {
    List<ModuleChildrensEntity> findByParent(Long id);
}
