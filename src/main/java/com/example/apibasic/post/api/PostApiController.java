package com.example.apibasic.post.api;

import com.example.apibasic.post.dto.PostCreateDTO;
import com.example.apibasic.post.dto.PostDetailResponseDTO;
import com.example.apibasic.post.dto.PostListResponseDTO;
import com.example.apibasic.post.dto.PostModifyDTO;
import com.example.apibasic.post.repository.PostRepository;
import com.example.apibasic.post.service.PostService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 리소스 : 게시물 (Post)
/*
     게시물 목록 조회:  /posts       - GET
     게시물 개별 조회:  /posts/{id}  - GET
     게시물 등록:      /posts       - POST
     게시물 수정:      /posts/{id}  - PATCH
     게시물 삭제:      /posts/{id}  - DELETE
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostApiController {

    private final PostService postService;

    // PostRepository에게 의존하는 관계
    private final PostRepository postRepository;

    //@Autowired // 스프링 컨테이너에게 의존객체를 자동주입해달라
//    public PostApiController(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }


    // 게시물 목록 조회
    @GetMapping
    public ResponseEntity<?> list() {
        log.info("/posts GET request");

        try {
            PostListResponseDTO listResponseDTO = postService.getList();

            return ResponseEntity
                    .ok()
                    .body(listResponseDTO)
                    ;
        } catch (RuntimeException e) {
            return ResponseEntity
                    .notFound()
                    .build()
                    ;
        }
    }


    // 게시물 개별 조회
    @GetMapping("/{postNo}")
    public ResponseEntity<?> detail(@PathVariable Long postNo) {
        log.info("/posts/{} GET request", postNo);
        try {
            PostDetailResponseDTO dto = postService.getDetail(postNo);

            return ResponseEntity
                    .ok()
                    .body(dto)
                    ;
        } catch (Exception e) {
            return ResponseEntity
                    .notFound()
                    .build()
                    ;
        }
    }

    // 게시물 등록
    @Parameters({
            @Parameter(name = "작성자", description = "게시물 작성자를 입력", example = "김철수")
    })

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody PostCreateDTO createDTO
                                    , BindingResult result  ) { // 검증 에러 정보를 갖고 있는 객체

        if(result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(err -> {
                log.warn("invalidated client data - {}", err.toString());
            });
            return ResponseEntity
                    .badRequest()
                    .body(fieldErrors);
        }
        log.info("/posts POST request");
        log.info("게시물 정보: {}", createDTO);


        try {
            PostDetailResponseDTO responseDTO = postService.insert(createDTO);
            return  ResponseEntity
                    .ok()
                    .body(responseDTO);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }

    }

    // 게시물 수정
    @PatchMapping("/{postNo}")
    public ResponseEntity<?> modify(
            @PathVariable Long postNo
            , @RequestBody PostModifyDTO modifyDTO
    ) {
        log.info("/posts/{} PATCH request", postNo);
        log.info("수정할 정보 : {}", modifyDTO);

        try {
            PostDetailResponseDTO responseDTO = postService.update(postNo,modifyDTO);
            return ResponseEntity
                    .ok()
                    .body(responseDTO);
        } catch (RuntimeException e) {
            log.error("update fail!! caused by - {}", e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }
    // 게시물 삭제
    @DeleteMapping("/{postNo}")
    public ResponseEntity<?> remove(@PathVariable Long postNo) {
        log.info("/posts/{} DELETE request", postNo);
        try {
            postService.delete(postNo);
            return ResponseEntity
                    .ok()
                    .body("Delete Success!!");
        } catch (RuntimeException e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }

    }

    // 1. 게시물 개별 조회시에는 클라이언트에게 추가로 수정시간 정보를 제공하세요
    // 2. 게시물 수정은 제목, 내용만 수정 가능해야 합니다.

}
