package org.samtar.cms.modules.accesscontrols.user.repository;

import org.samtar.cms.modules.accesscontrols.user.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity,Long> {
}
