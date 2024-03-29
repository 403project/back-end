package byulbyul.byulbyulpoll.controller.dto;

import lombok.Data;

@Data
public class MemberLoginRequestDto {
    private final String email;
    private final String password;

}
