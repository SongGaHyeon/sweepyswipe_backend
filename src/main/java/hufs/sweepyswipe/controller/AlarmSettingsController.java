package hufs.sweepyswipe.controller;

import hufs.sweepyswipe.domain.Member;
import hufs.sweepyswipe.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/members/alarm")
@RequiredArgsConstructor
public class AlarmSettingsController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping
    public String showAlarmSettings(HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginMember");

        Member existingMember = memberRepository.findOne(member.getId());
        model.addAttribute("member", existingMember);
        return "alarmSettings";
    }

    @PostMapping
    @Transactional
    public String updateAlarmSettings(HttpServletRequest request, @ModelAttribute("member") Member member) {
        HttpSession session = request.getSession();
        Member sessionMember = (Member) session.getAttribute("loginMember");

        Member existingMember = memberRepository.findOne(sessionMember.getId());

        existingMember.setEnabled(member.isEnabled());
        existingMember.setDayOfWeek(member.getDayOfWeek());
        existingMember.setTime(member.getTime());

        //memberRepository.save(existingMember);

        session.setAttribute("loginMember", existingMember);

        return "redirect:/members/alarm";

    }
}
