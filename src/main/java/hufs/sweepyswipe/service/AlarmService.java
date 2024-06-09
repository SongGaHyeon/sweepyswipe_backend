package hufs.sweepyswipe.service;

import hufs.sweepyswipe.domain.Member;
import hufs.sweepyswipe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlarmService {
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private MemberRepository memberRepository;

    @Scheduled(cron = "0 * * * * *")    //매분 0초마다 실행
    public void sendScheduledEmail() {
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek today = now.getDayOfWeek();
        int hour = now.getHour();
        int minute = now.getMinute();

        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            if(member.isEnabled() && member.getDayOfWeek() == today && member.getTime().getHour() == hour && member.getTime().getMinute() == minute) {
                sendEmail(member.getEmail());
            }
        }
    }

    private void sendEmail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("알림메일");
        message.setText("쓰레기 배출 시간입니다.");
        emailSender.send(message);
        log.info("send message : {}", message);
    }
}
