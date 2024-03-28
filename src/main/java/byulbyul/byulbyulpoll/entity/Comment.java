package byulbyul.byulbyulpoll.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Comment {

    @Id
    private long id;

    @ManyToOne(fetch= FetchType.LAZY)
    private Project project;

    private String commentBody;

    private String writer;

    private String password;

}
