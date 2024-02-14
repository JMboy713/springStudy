package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;


public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByID(Long id);// optional : null 일 경우 처리
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
