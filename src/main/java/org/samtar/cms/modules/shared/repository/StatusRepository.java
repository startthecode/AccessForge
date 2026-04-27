package org.samtar.cms.modules.shared.repository;

import org.samtar.cms.modules.shared.entity.StatusEntity;
import org.samtar.cms.modules.shared.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
Optional<StatusEntity> findByStatus(Status status);
}