package com.back.kukertonc.domain.summary.service;

import com.back.kukertonc.domain.summary.entity.UserSummary;
import com.back.kukertonc.domain.summary.entity.Writing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSummaryReader {
    private final UserSummaryRepository userSummaryRepository;
    public UserSummary getUserSummaryWithWritingId(Long writingId){
        return userSummaryRepository.findByWritingId(writingId);
    }

}
