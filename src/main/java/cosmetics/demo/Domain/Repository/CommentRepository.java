package cosmetics.demo.Domain.Repository;

import cosmetics.demo.Domain.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
