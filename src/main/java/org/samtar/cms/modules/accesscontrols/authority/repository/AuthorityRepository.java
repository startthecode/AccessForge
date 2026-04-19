package org.samtar.cms.modules.accesscontrols.authority.repository;

import org.samtar.cms.modules.accesscontrols.authority.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {

}