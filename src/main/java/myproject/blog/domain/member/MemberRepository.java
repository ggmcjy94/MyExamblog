package myproject.blog.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member , Long> {

    @Query("select m from Member m where username = :username and password = :password")
    Member findMember(String username, String password);
}
