package com.example.apibasic.post.dto;

import lombok.*;

import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder

public class PostListResponseDTO {
    private int count;
    private PageResponseDTO pageResponseDTO;
    private List<PostResponseDTO> posts;
}
