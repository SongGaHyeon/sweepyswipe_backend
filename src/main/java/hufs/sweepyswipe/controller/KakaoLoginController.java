package hufs.sweepyswipe.controller;

import hufs.sweepyswipe.domain.Member;
import hufs.sweepyswipe.service.KakaoService;
import hufs.sweepyswipe.service.KakaoUserInfoResponseDto;
import hufs.sweepyswipe.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("")
public class KakaoLoginController {

    private final KakaoService kakaoService;
    private final MemberService memberService;

    @GetMapping("/callback")
    public ResponseEntity<?> callback(@RequestParam("code") String code, HttpSession session) throws IOException {
        String accessToken = kakaoService.getAccessTokenFromKakao(code);

        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);

        //user 로그인, 회원가입 로직 추가

        Long kakaoId = userInfo.getId();
        String nickName = userInfo.getKakaoAccount().getProfile().getNickName();

        if (!memberService.isMemberExist(kakaoId)) {
            //회원가입
            Member newMember = new Member();
            newMember.setId(kakaoId);
            newMember.setName(nickName);
            memberService.join(newMember);
            log.info("join");
        }

        //세션에 로그인 정보 저장
        Member member = memberService.login(kakaoId);
        session.setAttribute("member", member);
        log.info("save to session");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
