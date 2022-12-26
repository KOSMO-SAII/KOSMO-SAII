package com.example.test.repository;

import com.example.test.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  MemberRepository extends JpaRepository<Member, Long> {
    Member findByLoginId(String loginId);

    Member findByNickname(String Nickname);

    Boolean existsByEmail(String Email);
    Boolean existsByNickname(String Nickname);

    Boolean existsByLoginId(String LoginId);

    Member findByEmail(String email);

    @Query("SELECT m from Member m where m.email = ?1")
    Optional<Member> findByEmails(@Param("email") String email);

  }
