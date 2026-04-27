package org.samtar.cms.modules.accesscontrols.department.repository;

import org.samtar.cms.modules.accesscontrols.department.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    Optional<DepartmentEntity> findByDepartment(String department);
}