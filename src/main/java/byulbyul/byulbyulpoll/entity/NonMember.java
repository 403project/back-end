package byulbyul.byulbyulpoll.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class NonMember {

    @Id
    private String ip;

    private Integer ageType;

    private String gender;

    private String occupation;
}
