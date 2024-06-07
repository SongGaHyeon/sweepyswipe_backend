package hufs.sweepyswipe.controller;

import hufs.sweepyswipe.domain.Member;
import hufs.sweepyswipe.service.KakaoService;
import hufs.sweepyswipe.service.KakaoUserInfoResponseDto;
import hufs.sweepyswipe.service.MemberService;
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
    public ResponseEntity<?> callback(@RequestParam("code") String code) throws IOException {
        String accessToken = kakaoService.getAccessTokenFromKakao(code);

        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);

        Long kakaoId = userInfo.getId();
        String userName = userInfo.getKakaoAccount().getProfile().getNickName();

        Member member = new Member();

        member.setId(kakaoId);
        member.setName(userName);

        memberService.join(member);

        //user 로그인, 회원가입 로직 추가
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
