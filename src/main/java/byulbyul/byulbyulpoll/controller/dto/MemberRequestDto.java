package byulbyul.byulbyulpoll.controller.dto;

import byulbyul.byulbyulpoll.service.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberRequestDto {
    private String email;
    private String password;
    private String nickname;
    private String gender;
    private Integer birthYear;
    private String occupation;

    public MemberDto toDto() {
        return new MemberDto(email, password, nickname, gender, birthYear, occupation);
    }
}
