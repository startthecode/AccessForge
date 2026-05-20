package org.samtar.cms.modules.accesscontrols.permission.repository;

import org.samtar.cms.modules.accesscontrols.permission.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
//    Optional<PermissionEntity> findByUser(Long id);
    List<PermissionEntity> findByCmsModuleModuleCodeAndUsersIsNull(String moduleCode);
    Optional<PermissionEntity> findByUsers_idAndCmsModule_ModuleCode(Long id,String moduleCode);
}