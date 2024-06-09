package hufs.sweepyswipe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class GuideBookController {

    @RequestMapping("/guidebook")
    public String guideBook() {
        log.info("GuideBook controller");
        return "guidebook";  // guidebook.html을 반환
    }
}
