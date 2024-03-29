package byulbyul.byulbyulpoll.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import byulbyul.byulbyulpoll.controller.dto.MemberLoginRequestDto;
import byulbyul.byulbyulpoll.controller.dto.MemberRequestDto;
import byulbyul.byulbyulpoll.controller.dto.MessageResponseDto;
import byulbyul.byulbyulpoll.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController("users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "이메일 중복 확인")
    @GetMapping("/unique-email")
    public MessageResponseDto isUniqueEmail(@RequestParam String email) {
        MessageResponseDto response = new MessageResponseDto();
        try {
            memberService.isValidEmail(email);
            response.setSuccess(true);
        } catch (IllegalArgumentException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());

        }
        return response;
    }

    @Operation(summary = "닉네임 중복 확인")
    @GetMapping("/unique-nickname")
    public MessageResponseDto isUniqueNickname(@RequestParam String nickname) {
        MessageResponseDto response = new MessageResponseDto();
        try {
            memberService.isValidNickname(nickname);
            response.setSuccess(true);
        } catch (IllegalArgumentException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    public MessageResponseDto signUp(@RequestBody MemberRequestDto memberRequestDto) {
        MessageResponseDto response = new MessageResponseDto();
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

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public MessageResponseDto login(@RequestBody MemberLoginRequestDto memberLoginRequestDto, HttpSession session) {
        MessageResponseDto response = new MessageResponseDto();
        try {
            memberService.login(memberLoginRequestDto.getEmail(), memberLoginRequestDto.getPassword(), session);
            response.setSuccess(true);
            response.setMessage("로그인에 성공했습니다.");
        } catch (IllegalArgumentException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
