package byulbyul.byulbyulpoll.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import byulbyul.byulbyulpoll.controller.dto.MemberLoginDto;
import byulbyul.byulbyulpoll.controller.dto.MemberRequestDto;
import byulbyul.byulbyulpoll.controller.dto.MessageDto;
import byulbyul.byulbyulpoll.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController("users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/unique-email")
    public MessageDto isUniqueEmail(@RequestParam String email) {
        MessageDto response = new MessageDto();
        try {
            memberService.isValidEmail(email);
            response.setSuccess(true);
        } catch (IllegalArgumentException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());

        }
        return response;
    }

    @GetMapping("/unique-nickname")
    public MessageDto isUniqueNickname(@RequestParam String nickname) {
        MessageDto response = new MessageDto();
        try {
            memberService.isValidNickname(nickname);
            response.setSuccess(true);
        } catch (IllegalArgumentException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping("/sign-up")
    public MessageDto signUp(@RequestBody MemberRequestDto memberRequestDto) {
        MessageDto response = new MessageDto();
        try {
            memberService.signUp(memberRequestDto.toDto());
            response.setSuccess(true);
            response.setMessage("회원가입에 성공했습니다.");
        } catch (IllegalArgumentException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping("/login")
    public MessageDto login(@RequestBody MemberLoginDto memberLoginDto, HttpSession session) {
        MessageDto response = new MessageDto();
        try {
            memberService.login(memberLoginDto.getEmail(), memberLoginDto.getPassword(), session);
            response.setSuccess(true);
            response.setMessage("로그인에 성공했습니다.");
        } catch (IllegalArgumentException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
