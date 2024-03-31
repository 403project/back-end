package byulbyul.byulbyulpoll.repository;

import byulbyul.byulbyulpoll.entity.Project;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByPollId(long pollId, Sort sort);

    Optional<Project> findByTitle(String title);
}
