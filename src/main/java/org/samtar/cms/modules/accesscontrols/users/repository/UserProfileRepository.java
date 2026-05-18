package org.samtar.cms.modules.accesscontrols.users.repository;

import org.samtar.cms.modules.accesscontrols.users.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity,Long> {
}
