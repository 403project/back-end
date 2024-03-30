package byulbyul.byulbyulpoll.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Vote {
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    private boolean isMember;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    private NonMember nonMember;

    private Integer ageType;

    private String gender;

    private String occupation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Poll poll;

    public Vote(Project project, boolean isMember, Member member, NonMember nonMember, Integer ageType, String gender, String occupation, Poll poll) {
        this.project = project;
        this.isMember = isMember;
        this.member = member;
        this.nonMember = nonMember;
        this.ageType = ageType;
        this.gender = gender;
        this.occupation = occupation;
        this.poll = poll;
    }

    public static Vote voteByMember(Project project, Member member){

        var ageType = (LocalDateTime.now().getYear() - member.getBirthYear()) / 10 * 10;
        return new Vote(project, true, member, null, ageType, member.getGender(), member.getOccupation(), project.getPoll());
    }

}
