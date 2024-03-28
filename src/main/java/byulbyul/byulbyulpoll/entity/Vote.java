package byulbyul.byulbyulpoll.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Vote {
    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Project project;

    private boolean isMember;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    private NonMember nonMember;

}
