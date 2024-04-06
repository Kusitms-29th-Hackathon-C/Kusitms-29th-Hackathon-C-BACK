package com.back.kukertonc.domain.summary.service;

import com.back.kukertonc.domain.gpt.service.GptTextReader;
import com.back.kukertonc.domain.summary.dto.response.WritingInfoResponse;
import com.back.kukertonc.domain.summary.dto.response.WritingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WritingService {
    private final WritingReader writingReader;
    private final UserSummaryReader userSummaryReader;
    private final GptTextReader gptTextReader;
    public List<WritingResponse> getWritingList(){
        List<WritingResponse> writingResponses = new ArrayList<>();
        for(int i=1; i<4;i++){
            List<WritingInfoResponse> writingInfoResponses = writingReader.getWritingAllWithType(i).stream().map(writing ->
                    WritingInfoResponse.of(
                            writing,
                            userSummaryReader.getUserSummaryIsCompleteWithWritingId(writing.getId())
                    )).toList();
            writingResponses.add(WritingResponse.of(writingReader.getType(writingInfoResponses.get(0).getWritingId()),writingInfoResponses));
        }

        return writingResponses;
    }
    public String getContent(Long writingId){
        return gptTextReader.getContent(writingId).getContent();
    }
}
