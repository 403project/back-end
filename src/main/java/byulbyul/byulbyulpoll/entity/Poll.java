package byulbyul.byulbyulpoll.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Poll {

    @Id
    private long id;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public Poll(String title, LocalDateTime startDate, LocalDateTime endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Poll() {

    }
}
