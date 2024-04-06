package com.back.kukertonc.domain.summary.service;

import com.back.kukertonc.domain.summary.entity.Writing;
import com.back.kukertonc.domain.summary.repository.WritingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WritingReader {
    private final WritingRepository writingRepository;
    public List<Writing> getWritingAll(){
        return writingRepository.findAll();
    }
}
