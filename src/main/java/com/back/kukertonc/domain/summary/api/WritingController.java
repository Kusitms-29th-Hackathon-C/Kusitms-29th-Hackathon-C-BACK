package com.back.kukertonc.domain.summary.api;

import com.back.kukertonc.domain.summary.dto.response.WritingInfoResponse;
import com.back.kukertonc.domain.summary.dto.response.WritingResponse;
import com.back.kukertonc.domain.summary.service.WritingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/writing")
@RequiredArgsConstructor
public class WritingController {
    private final WritingService writingService;
    @GetMapping
    public ResponseEntity<List<WritingResponse>> getWritingResponse(){
        List<WritingResponse> writingInfoResponses = writingService.getWritingList();
        return new ResponseEntity<>(writingInfoResponses, HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<String> getContent(@RequestParam(name = "writingId") Long writingId){
        String content = writingService.getContent(writingId);
        return new ResponseEntity<>(content, HttpStatus.OK);
    }
}
