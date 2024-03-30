package byulbyul.byulbyulpoll.repository;

import byulbyul.byulbyulpoll.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Long> {
}
