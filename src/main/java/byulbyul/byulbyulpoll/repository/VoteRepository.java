package byulbyul.byulbyulpoll.repository;

import byulbyul.byulbyulpoll.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findByProject_IdAndMember_Email(Long projectId, String memberEmail);


}
