package com.back.kukertonc.domain.summary.repository;

import com.back.kukertonc.domain.summary.entity.Writing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WritingRepository extends JpaRepository<Writing, Long> {
    List<Writing> findAllByType(String type);
}
