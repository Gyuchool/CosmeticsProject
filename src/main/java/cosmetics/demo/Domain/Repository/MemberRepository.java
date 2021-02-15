package cosmetics.demo.Domain.Repository;

import cosmetics.demo.Domain.Entity.Member.MemberEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(MemberEntity member) {
        em.persist(member);
        return member.getId();
    }
    public MemberEntity findOne(Long id) {
        return em.find(MemberEntity.class, id);
    }
    public List<MemberEntity> findAll() {
        return em.createQuery("select m from MemberEntity m", MemberEntity.class)
                .getResultList();
    }
    public List<MemberEntity> findByName(String name) {
        return em.createQuery("select m from MemberEntity m where m.name = :name",
                MemberEntity.class)
                .setParameter("name", name)
                .getResultList();
    }
}