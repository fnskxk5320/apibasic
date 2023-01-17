package com.example.apibasic.post.dto;


// 클라이언트에게 응답할 페이지 정보

/*
        {
                "startPage" : 1,
                "endPage" : 10,
                "currentPage" : 3,
                "prev" : false,
                "next" : true,
                "totalCount" : 500
        }
 */


import com.example.apibasic.post.entity.PostEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

@ToString @Setter @Getter
public class PageResponseDTO <T> {

    private int startPage;
    private int endPage;
    private int currentPage;

    private boolean prev;
    private boolean next;

    private int totalCount;

    // 페이지 개수
    private static final int PAGE_COUNT = 10;

    public PageResponseDTO(Page<T> pageData ) {
        this.totalCount = (int) pageData.getTotalElements();
        this.currentPage = pageData.getPageable().getPageNumber() + 1;
        this.endPage = (int) (Math.ceil((double) currentPage / PAGE_COUNT) * PAGE_COUNT);
        this.startPage = endPage - PAGE_COUNT + 1;

        // 페이지 마지막 구간에서 endPage 값 보정
        // 실제 끝페이지 숫자를 구함

//        int realEnd = (int) Math.ceil(totalCount / pageData.getSize());
        int realEnd = pageData.getTotalPages();

        // 언제 보정해야할까?? -> 마지막 구간에서만
        if(realEnd < this.endPage) {
            this.endPage = realEnd;
        }
        this.prev = startPage > 1;
        this.next = endPage < realEnd;
    }
}
