package byulbyul.byulbyulpoll.repository;

import byulbyul.byulbyulpoll.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {
}
