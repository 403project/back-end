package byulbyul.byulbyulpoll.repository;

import byulbyul.byulbyulpoll.entity.NonMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NonMemberRepository extends JpaRepository<NonMember, String> {
}
