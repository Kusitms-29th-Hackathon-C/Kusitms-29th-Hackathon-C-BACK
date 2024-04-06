package com.back.kukertonc.domain.gpt.service;

import com.back.kukertonc.domain.gpt.entity.GptText;
import com.back.kukertonc.domain.gpt.repository.GptTextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GptTextAppender {
    private final GptTextRepository gptTextRepository;
    public GptText save(GptText gptText){
        return gptTextRepository.save(gptText);
    }
}
