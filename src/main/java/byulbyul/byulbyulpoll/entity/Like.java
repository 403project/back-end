package byulbyul.byulbyulpoll.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Entity
@Getter @Setter
public class Like {

    @Id
    long id;

    @ManyToOne
    private Project project;

    private boolean isMember;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    private NonMember nonMember;

}
