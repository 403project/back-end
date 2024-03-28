package byulbyul.byulbyulpoll.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ProjectImage {
    @Id
    private long id;

    @ManyToOne
    private Project project;

    private int imageOrder;
}
