package com.example.apibasic.post.entity;

import com.example.apibasic.post.dto.PostResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

// 게시물의 데이터 자바빈즈
@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="postNo")
@Builder
@Entity
@Table(name="tbl_post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_no")  // JPA에 의해 자동으로 post_no로 바뀜
    private Long postNo; // 게시물 식별번호
    @Column(nullable = false)
    private String writer; // 작성자
    @Column(nullable = false)
    private String title; // 제목

    private String content; // 내용
   // private List<String> hashTags; // 해시태그 목록

    @CreationTimestamp
    private LocalDateTime createDate; // 작성 시간
    @UpdateTimestamp
    private LocalDateTime modifyDate; // 수정 시간




}
