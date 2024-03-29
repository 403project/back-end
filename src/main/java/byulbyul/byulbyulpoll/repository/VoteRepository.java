package byulbyul.byulbyulpoll.repository;

import byulbyul.byulbyulpoll.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
