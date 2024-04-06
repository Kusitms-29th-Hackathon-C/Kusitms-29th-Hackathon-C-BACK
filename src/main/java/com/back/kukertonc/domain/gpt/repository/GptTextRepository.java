package com.back.kukertonc.domain.gpt.repository;

import com.back.kukertonc.domain.gpt.entity.GptText;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GptTextRepository extends MongoRepository<GptText, String> {


    GptText findByWritingId(Long writingId);

}
