package hufs.sweepyswipe.interceptor;

import hufs.sweepyswipe.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //세션에서 정보 조회
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginMember");

        //회원 정보 체크
        if (member == null) {
            log.info("member is null");
            response.sendRedirect("/member/login");
            return false;
        }

        log.info("member is not null");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
