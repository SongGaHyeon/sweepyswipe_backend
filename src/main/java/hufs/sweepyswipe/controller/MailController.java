package hufs.sweepyswipe.controller;

import hufs.sweepyswipe.service.MailDto;
import hufs.sweepyswipe.service.MailService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {
    @Resource(name = "mailService")
    private MailService mailService;
    @GetMapping("/contact/send")
    public void mail() {
        mailService.sendSimpleMessage();
    }
}
