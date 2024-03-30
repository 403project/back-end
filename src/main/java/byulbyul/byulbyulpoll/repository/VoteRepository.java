package byulbyul.byulbyulpoll.repository;

import byulbyul.byulbyulpoll.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByMemberAndProject(long projectId, String memberEmail);
}
