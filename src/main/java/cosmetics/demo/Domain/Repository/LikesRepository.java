package cosmetics.demo.Domain.Repository;

import cosmetics.demo.Domain.Entity.BoardEntity;
import cosmetics.demo.Domain.Entity.LikesFunction;
import cosmetics.demo.Domain.Entity.Member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<LikesFunction, Long> {
    Optional<LikesFunction> findByMemberEntityAndBoardEntity(MemberEntity member, BoardEntity board);
    int countByBoardEntity(BoardEntity boardEntity);
}
