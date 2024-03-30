package byulbyul.byulbyulpoll.service.dto;

import lombok.Setter;
import org.springframework.lang.NonNull;

import byulbyul.byulbyulpoll.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDto {
    private String email;
    @Setter
    private String password;
    private String nickname;
    private String gender;
    private Integer birthYear;
    private String occupation;

    @NonNull
    public Member toEntity() {
        return new Member(email, password, nickname, gender, birthYear, occupation);
    }
}
