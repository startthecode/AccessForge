package org.samtar.cms.modules.accesscontrols.designation.repository;

import org.samtar.cms.modules.accesscontrols.designation.entity.DesignationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DesignationRepository extends JpaRepository<DesignationEntity, Long> {

}