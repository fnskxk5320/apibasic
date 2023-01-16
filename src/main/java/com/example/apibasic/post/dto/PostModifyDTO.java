package com.example.apibasic.post.dto;


import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class PostModifyDTO {
    private String title;
    private String content;
}
