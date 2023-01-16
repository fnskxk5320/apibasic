package com.example.apibasic.jpabasic.repository;

import com.example.apibasic.jpabasic.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA로 CRUD Operation을 하려면 JpaRepository 인터페이스를 상속
// 제네릭 타입으로 첫번째로 CRUD를 엔티티 클래스의 타입, 두번째로 해당 엔티티의 ID의 타입
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
