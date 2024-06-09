package hufs.sweepyswipe.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage() {

        SimpleMailMessage message = new SimpleMailMessage();
        //message.setFrom(data.getEmail());
        message.setTo("kimhajin01@naver.com");  //보낼 메일
        message.setSubject("제목없음");
        message.setText("ㅎㅇ");
        emailSender.send(message);

        log.info("success message {} : ", message);

    }
}
