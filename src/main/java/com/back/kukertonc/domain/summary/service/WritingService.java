package com.back.kukertonc.domain.summary.service;

import com.back.kukertonc.domain.summary.dto.response.WritingInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WritingService {
    private final WritingReader writingReader;
    private final UserSummaryReader userSummaryReader;
    public List<WritingInfoResponse> getWritingList(){
        return writingReader.getWritingAll().stream().map(writing ->
                WritingInfoResponse.of(
                writing,
                userSummaryReader.getUserSummaryWithWritingId(writing.getId()).isComplete()
                )).toList();
    }
}
