package org.samtar.cms.modules.accesscontrols.branch.repository;

import org.samtar.cms.modules.accesscontrols.branch.entity.BranchEntity;
import org.samtar.cms.modules.accesscontrols.department.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<BranchEntity, Long> {
    Optional<BranchEntity> findByBranch(String branch);

}