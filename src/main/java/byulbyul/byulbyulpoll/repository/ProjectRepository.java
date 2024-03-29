package byulbyul.byulbyulpoll.repository;

import byulbyul.byulbyulpoll.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
