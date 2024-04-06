package com.back.kukertonc.domain.gpt.api;

import com.back.kukertonc.domain.gpt.dto.request.ChatCompletionDto;
import com.back.kukertonc.domain.gpt.dto.response.GptResponse;
import com.back.kukertonc.domain.gpt.service.GptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/gpt")
@RequiredArgsConstructor
public class GptController {
    private final GptService gptService;
    @GetMapping("/prompt")
    public ResponseEntity< List<String> > selectPrompt() throws IOException {
        List<String> result = gptService.prompt("word");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/text")
    public ResponseEntity< GptResponse > getLongText() throws IOException {
        GptResponse result = gptService.getText("text");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
