package com.edomew.Auth.security.repository;

import com.edomew.Auth.security.models.UserMy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserMy, Long> {
    Optional<UserMy> findByUsername(String username);

    boolean existsByUsername(String username);
}
