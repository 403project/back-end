package byulbyul.byulbyulpoll.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    public Tag(String name, Project project) {
        this.name = name;
        this.project = project;
    }
}
