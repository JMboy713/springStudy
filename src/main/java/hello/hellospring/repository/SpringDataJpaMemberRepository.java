//package hello.hellospring.repository;
//
//import hello.hellospring.domain.Member;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository { // interface가 interface를 받을 때는 extends라고 한다.
//
//    @Override
//    Optional<Member> findByName(String name); // jpa의 기능들을 메서드로 다 만들어놓은것.
//
//}
