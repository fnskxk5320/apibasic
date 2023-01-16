package com.example.apibasic.post.dto;

import com.example.apibasic.post.entity.PostEntity;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PostCreateDTO {

    /*
    NotNull : null 값일 경우 에러 발생
    NotEmpty : 빈문자일 경우 에러 발생
    NotBlank : null 이거나 빈문자일 경우 에러 발생
     */

    @NotBlank
    @Size(min = 2, max = 5)
    private String writer;
    @NotBlank
    @Min(1) @Max(20)
    private String title;
    private String content;
    private List<String> hashTags;

    // PostEntity로 변환하는 유틸 메서드
    public PostEntity toEntity() {
        return PostEntity.builder()
                .postNo(PostEntity.sequence++)
                .writer(this.writer)
                .content(this.content)
                .title(this.title)
                .hashTags(this.hashTags)
                .createDate(LocalDateTime.now())
                .build();
    }

}
