package org.samtar.cms.modules.accesscontrols.customModules.repository;

import org.samtar.cms.modules.accesscontrols.customModules.entity.CustomModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomModuleRepository extends JpaRepository<CustomModuleEntity, Long> {
    Optional<CustomModuleEntity> findByModuleName(String name);
}