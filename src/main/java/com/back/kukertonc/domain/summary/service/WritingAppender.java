package com.back.kukertonc.domain.summary.service;

import com.back.kukertonc.domain.summary.entity.Writing;
import com.back.kukertonc.domain.summary.repository.WritingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WritingAppender {
    private final WritingRepository writingRepository;
    public Writing save(Writing writing){
        return writingRepository.save(writing);
    }
}
