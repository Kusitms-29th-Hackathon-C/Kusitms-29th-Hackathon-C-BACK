package com.back.kukertonc.domain.summary.repository;

import com.back.kukertonc.domain.summary.entity.UserSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSummaryRepository extends JpaRepository<UserSummary, Long> {
    Optional<UserSummary> findByWritingId(Long writingId);
}
