package com.back.kukertonc.domain.gpt.repository;

import com.back.kukertonc.domain.gpt.entity.GptText;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.*;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.query.Param;

@EnableMongoRepositories
public interface GptTextRepository extends MongoRepository<GptText, String> {
    GptText findByWritingId(Long Id);
}
