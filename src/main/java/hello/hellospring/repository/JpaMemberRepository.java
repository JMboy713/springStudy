package hello.hellospring.repository;

import com.fasterxml.jackson.databind.JsonSerializer;
import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.boot.model.source.internal.hbm.XmlElementMetadata;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    // jpa data-jpa 가 엔티티 매니저를 가져온다. 해당 매니저를 injection 받기만 하면 된다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);// 영속 (db에 저장)
        return member;
    }

    @Override
    public Optional<Member> findByID(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name=:name", Member.class).setParameter("name", name).getResultList();
        return result.stream().findAny();

    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();

    }
}
