package byulbyul.byulbyulpoll.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Likes {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    private Project project;

    private boolean isMember;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    private NonMember nonMember;

}
