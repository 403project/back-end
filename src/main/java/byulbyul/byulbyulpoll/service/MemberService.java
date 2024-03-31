package byulbyul.byulbyulpoll.service;

import byulbyul.byulbyulpoll.entity.Member;
import byulbyul.byulbyulpoll.repository.MemberRepository;
import byulbyul.byulbyulpoll.service.dto.MemberDto;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member signUp(MemberDto memberDto) {
        if (isValidEmail(memberDto.getEmail()) && isValidNickname(memberDto.getNickname())
                && isValidBirthYear(memberDto.getBirthYear()) && isValidPassword(memberDto.getPassword())) {
            memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
            memberRepository.save(memberDto.toEntity());
            return memberRepository.findByEmail(memberDto.getEmail()).orElseThrow();
        }
        return null;
    }

    public boolean isValidEmail(String email) {
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new IllegalArgumentException("이메일 형식이 올바르지 않습니다.");
        }
        if (memberRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이메일이 이미 존재합니다.");
        }
        return true;
    }

    public boolean isValidNickname(String nickname) {
        if (nickname.length() < 2 || nickname.length() > 8) {
            throw new IllegalArgumentException("닉네임은 2자 이상 8자 이하로 입력해주세요.");
        }
        if (memberRepository.existsByNickname(nickname)) {
            throw new IllegalArgumentException("닉네임이 이미 존재합니다.");
        }
        return true;
    }

    public boolean isValidBirthYear(int birthYear) {
        if (birthYear < 1924 || birthYear > 2009) {
            throw new IllegalArgumentException("가입이 가능한 나이가 아닙니다.");
        }
        return true;
    }

    public boolean isValidPassword(String password) {
        if (password.length() < 6 || password.length() > 15) {
            throw new IllegalArgumentException("비밀번호는 6자 이상 15자 이하로 입력해주세요.");
        }
        return true;
    }

    public boolean login(String email, String password, HttpSession session) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        session.setAttribute("member", member);
        return true;

    }
}
