package com.back.kukertonc.domain.gpt.service;

import com.back.kukertonc.domain.gpt.entity.GptText;
import com.back.kukertonc.domain.gpt.repository.GptTextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GptTextReader {
    private final GptTextRepository gptTextRepository;
    public GptText getContent(String writingId){
        return gptTextRepository.findByWritingId(writingId);
    }
}
