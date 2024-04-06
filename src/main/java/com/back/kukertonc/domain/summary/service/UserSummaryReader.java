package com.back.kukertonc.domain.summary.service;

import com.back.kukertonc.domain.summary.entity.UserSummary;
import com.back.kukertonc.domain.summary.entity.Writing;
import com.back.kukertonc.domain.summary.repository.UserSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSummaryReader {
    private final UserSummaryRepository userSummaryRepository;
    public boolean getUserSummaryIsCompleteWithWritingId(Long writingId){
        if(userSummaryRepository.findByWritingId(writingId).isPresent())
            return userSummaryRepository.findByWritingId(writingId).get().isComplete();
        return false;
    }

}
