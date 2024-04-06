package com.back.kukertonc.domain.summary.repository;

import com.back.kukertonc.domain.summary.entity.UserSummary;
import com.back.kukertonc.domain.summary.entity.Writing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface UserSummaryRepository extends JpaRepository<UserSummary, Long> {
    List<UserSummary> findTop3ByWritingOrderByCreateAt(Writing writing);
}
