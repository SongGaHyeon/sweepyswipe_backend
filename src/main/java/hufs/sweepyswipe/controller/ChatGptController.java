package hufs.sweepyswipe.controller;

import hufs.sweepyswipe.Dto.ChatGptResponseDto;
import hufs.sweepyswipe.Dto.QuestionRequestDto;
import hufs.sweepyswipe.service.ChatGptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatGptController {

    @Autowired
    private ChatGptService chatGptService;

    @PostMapping("/question")
    public ChatGptResponseDto askQuestion(@RequestBody QuestionRequestDto questionRequestDto) {
        return chatGptService.getChatGptResponse(questionRequestDto);
    }
}

