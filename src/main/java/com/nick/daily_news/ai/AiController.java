package com.nick.daily_news.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AiController {
    private final AiService service;

    @GetMapping("ai/chat")
    public String modelTest(String msg){
        return service.modelTest(msg);
    }
}
