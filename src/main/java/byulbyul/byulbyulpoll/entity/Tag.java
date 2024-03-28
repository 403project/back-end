package byulbyul.byulbyulpoll.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Tag {

    @Id
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;
}
