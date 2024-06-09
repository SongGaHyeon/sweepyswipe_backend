package hufs.sweepyswipe.service;

import hufs.sweepyswipe.domain.Member;
import hufs.sweepyswipe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    @Transactional(readOnly = false)
    public Long join(Member member) {
        //validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public boolean isMemberExist(Long id) {
        Member findMember = memberRepository.findOne(id);
        return findMember != null;
    }

    /*
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findOne(member.getId());
        if(findMember != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }
     */

    @Transactional(readOnly = false)
    public Member login(Long id) {
        Member member = memberRepository.findOne(id);
        return member;
    }

}
