package org.samtar.cms.modules.shared.repository;

import org.samtar.cms.modules.shared.entity.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenderRepository extends JpaRepository<GenderEntity, Long> {
   Optional<GenderEntity> findByGender(String gender);
}