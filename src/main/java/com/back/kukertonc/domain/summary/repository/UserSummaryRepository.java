package com.back.kukertonc.domain.summary.repository;

import com.back.kukertonc.domain.summary.entity.UserSummary;
import com.back.kukertonc.domain.summary.entity.Writing;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface UserSummaryRepository extends JpaRepository<UserSummary, Long> {
    List<UserSummary> findTop3ByWritingOrderByCreateAt(Writing writing);

    Optional<UserSummary> findByWritingId(Long writingId);
}
