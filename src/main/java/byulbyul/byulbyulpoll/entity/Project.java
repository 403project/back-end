package byulbyul.byulbyulpoll.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Poll poll;

    private String title;

    private String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectImage> projectImageList;

    private int voteCount;

    public Project(Poll poll, String title, String description) {
        this.poll = poll;
        this.title = title;
        this.description = description;
        this.voteCount = 0;
    }

    public Project() {

    }

    public void upvote() {
        this.voteCount++;
    }
}
