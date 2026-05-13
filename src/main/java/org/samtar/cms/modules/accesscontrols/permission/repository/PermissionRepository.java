package org.samtar.cms.modules.accesscontrols.permission.repository;

import org.samtar.cms.modules.accesscontrols.permission.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    Optional<PermissionEntity> findByUser(Long id);
    Optional<PermissionEntity> findByUserAndCmsModule(Long id,Long moduleID);
}