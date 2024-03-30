package byulbyul.byulbyulpoll.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProjectImage {
    @Id
    @GeneratedValue
    private Long id;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    private int imageOrder;

    public ProjectImage(Project project, String imageUrl,int imageOrder ){
        this.project = project;
        this.imageUrl = imageUrl;
        this.imageOrder = imageOrder;
    }
}
