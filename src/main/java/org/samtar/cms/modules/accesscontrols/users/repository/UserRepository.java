package org.samtar.cms.modules.accesscontrols.users.repository;

import org.samtar.cms.modules.accesscontrols.users.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    //    Optional<UserEntity> findByUsernameAndPassword(String email);
}
