package com.back.kukertonc.domain.summary.service;

import com.back.kukertonc.domain.summary.entity.Writing;
import com.back.kukertonc.domain.summary.repository.WritingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WritingReader {
    private final WritingRepository writingRepository;
    public List<Writing> getWritingAll(){
        return writingRepository.findAll();
    }
    public String getType(Long writingId){
        return writingRepository.findById(writingId).get().getType();
    }
    public List<Writing> getWritingAllWithType(int count){
        if(count==1)
            return writingRepository.findAllByType("인문 예술");
        if(count==2)
            return writingRepository.findAllByType("사회 문화");
        if(count==3)
            return writingRepository.findAllByType("과학 기술");
        return null;
    }
}
