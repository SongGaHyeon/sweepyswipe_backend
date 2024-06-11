package hufs.sweepyswipe.service;

import hufs.sweepyswipe.Dto.ChatGptRequestDto;
import hufs.sweepyswipe.Dto.ChatGptResponseDto;
import hufs.sweepyswipe.Dto.QuestionRequestDto;
import hufs.sweepyswipe.config.ChatGptConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class ChatGptService {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = ChatGptConfig.API_KEY;

    public ChatGptResponseDto getChatGptResponse(QuestionRequestDto questionRequestDto) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_KEY);

        System.out.println("API_KEY: " + API_KEY);

        ChatGptRequestDto.Message message = ChatGptRequestDto.Message.builder()
                .role("user")
                .content(questionRequestDto.getQuestion())
                .build();

        ChatGptRequestDto request = ChatGptRequestDto.builder()
                .model("gpt-3.5-turbo")
                .messages(Collections.singletonList(message))
                .maxTokens(1000)
                .temperature(0.7)
                .topP(0.9)
                .build();

        HttpEntity<ChatGptRequestDto> entity = new HttpEntity<>(request, headers);

        try {
            return restTemplate.postForObject(API_URL, entity, ChatGptResponseDto.class);
        } catch (HttpClientErrorException e) {
            System.err.println("HTTP Client Error: " + e.getStatusCode());
            System.err.println("Response Body: " + e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to get response from ChatGPT API", e);
        }
    }
}
