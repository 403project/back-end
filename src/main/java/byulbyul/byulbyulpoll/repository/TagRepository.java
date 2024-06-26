package byulbyul.byulbyulpoll.repository;

import byulbyul.byulbyulpoll.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByProjectId(Long projectId);
}
