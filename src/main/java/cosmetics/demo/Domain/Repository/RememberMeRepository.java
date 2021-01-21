package cosmetics.demo.Domain.Repository;

import cosmetics.demo.Domain.Entity.PersistentLogins;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RememberMeRepository extends JpaRepository<PersistentLogins, Long> {
    Optional<PersistentLogins> deleteByName(String name);
}
