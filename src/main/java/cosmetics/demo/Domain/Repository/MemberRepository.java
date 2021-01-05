package cosmetics.demo.Domain.Repository;

import cosmetics.demo.Domain.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    MemberEntity findByEmail(String email);
    MemberEntity findByPassword(String password);
}
