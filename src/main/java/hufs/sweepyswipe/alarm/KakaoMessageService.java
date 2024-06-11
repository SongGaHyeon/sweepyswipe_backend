import hufs.sweepyswipe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class KakaoMessageService {

    @Autowired
    private UserRepository userRepository;

    public void sendKakaoMessage(String userId, String message) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            sendMessageToKakao(user.getAccessToken(), message);
        }
    }

    private void sendMessageToKakao(String accessToken, String message) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> params = new HashMap<>();
        params.put("template_object", getMessageTemplate(message));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(params, headers);
        restTemplate.postForEntity(url, entity, String.class);
    }

    private Map<String, Object> getMessageTemplate(String message) {
        Map<String, Object> templateObject = new HashMap<>();
        templateObject.put("object_type", "text");//object_type의 키에 text 값을 설정.
        templateObject.put("text", message);
        templateObject.put("link", new HashMap<>());  // 필요에 따라 링크 추가

        return templateObject;
    }
}
