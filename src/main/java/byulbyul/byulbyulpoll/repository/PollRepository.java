package byulbyul.byulbyulpoll.repository;

import byulbyul.byulbyulpoll.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PollRepository extends JpaRepository<Poll, Long> {

    Optional<Poll> findByTitle(String title);

    Boolean existsByTitle(String title);
}
