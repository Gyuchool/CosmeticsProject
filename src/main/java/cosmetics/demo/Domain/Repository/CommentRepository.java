package cosmetics.demo.Domain.Repository;

import cosmetics.demo.Domain.Entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    Page<CommentEntity> findCommentEntityByBoardEntity_Id(Long boardId, Pageable pageable);
}
