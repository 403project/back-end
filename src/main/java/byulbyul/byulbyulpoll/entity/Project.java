package byulbyul.byulbyulpoll.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Project {

    @Id
    private long id;

    @ManyToOne
    private Poll poll;

    private String title;

    private String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectImage> projectImageList;

    private int upvote;

    public Project(long pollId, String title, String description) {
        this.id = pollId;
        this.title = title;
        this.description = description;
    }

    public Project() {

    }

    public void upvote() {
        this.upvote++;
    }
}
