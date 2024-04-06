package com.back.kukertonc.domain.user.repository;

import com.back.kukertonc.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
