package com.back.kukertonc.domain.summary.repository;

import com.back.kukertonc.domain.summary.entity.UserSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSummaryRepository extends JpaRepository<UserSummary, Long> {
}
