package com.example.apibasic.jpabasic.repository;

import com.example.apibasic.jpabasic.entity.Gender;
import com.example.apibasic.jpabasic.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.reflect.Member;
import java.util.List;

// JPA로 CRUD Operation을 하려면 JpaRepository 인터페이스를 상속
// 제네릭 타입으로 첫번째로 CRUD를 엔티티 클래스의 타입, 두번째로 해당 엔티티의 ID의 타입
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    // 쿼리 메서드
    List<MemberEntity> findByGender(Gender gender);

    List<MemberEntity> findByAccountAndGender(String account, Gender gender);

    List<MemberEntity> findByNickNameContaining(String nickName);

    // 간단한 쿼리는 JPA를 사용하지만 복잡한 쿼리는 보통 쿼리 DSL을 사용

    // JPQL 사용
    // select 별칭 from 엔티티 클래스명 as 별칭 where 별칭.필드명
    // native-sql : select m.userCode from tbl_member as m
    // jpql : select m.userId from MemberEntity as m
    // 계정명으로 회원 조회
    @Query("SELECT m FROM MemberEntity as m WHERE m.account=:acc") //?1 <- 첫번째 매개변수
    MemberEntity getMemberByAccount(@Param("acc") String acc);

    // 닉네임과 성별 동시만족 조건으로 회원 조회
    @Query("SELECT m FROM MemberEntity m WHERE m.nickName=:nick and m.gender=:gen")
    List<MemberEntity> getMembersByNickAndGender(@Param("nick") String nick, @Param("gen") Gender gen);

    @Query("SELECT m FROM MemberEntity m WHERE m.nickName LIKE %:nick%")
    List<MemberEntity> getMembersByNickName(@Param("nick") String nick);

    @Modifying // 수정, 삭제할 때 붙이기
    @Query("delete from MemberEntity m where m.nickName=?1")
    void deleteByNickName(String nickName);

}
