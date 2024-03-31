package byulbyul.byulbyulpoll.repository;

import byulbyul.byulbyulpoll.entity.ProjectImage;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectImageRepository extends JpaRepository<ProjectImage, Long> {

    List<ProjectImage> findByProjectId(Long projectId, Sort sort);

    ProjectImage findFirstByProjectId(Long projectId, Sort sort);

}
