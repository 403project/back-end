package byulbyul.byulbyulpoll.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {

    @Id
    private String email;

    private String password;

    private String nickname;

    private String gender;

    private Integer birthYear;

    private String occupation;
}
