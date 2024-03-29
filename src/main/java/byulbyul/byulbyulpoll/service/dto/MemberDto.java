package byulbyul.byulbyulpoll.service.dto;

import org.springframework.lang.NonNull;

import byulbyul.byulbyulpoll.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDto {
    private String email;
    private String password;
    private String nickname;
    private String gender;
    private Integer birthYear;
    private String occupation;

    public void setPassword(String password) {
        this.password = password;
    }

    @NonNull
    public Member toEntity() {
        return new Member(email, password, nickname, gender, birthYear, occupation);
    }
}
