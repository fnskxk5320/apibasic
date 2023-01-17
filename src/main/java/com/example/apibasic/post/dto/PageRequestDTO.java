package com.example.apibasic.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder @ToString
public class PageRequestDTO {
    private int page; // 요청한 페이지 번호
    private int sizePerPage; // 한 페이지에 보여줄 데이터 수

    // 초기 렌더링시에 사용될 데이터
    public PageRequestDTO() {
        this.page = 1;
        this.sizePerPage = 10;
    }

    public void setPage(int page) {
        if(page < 0 || page > Integer.MAX_VALUE)
        {   this.page = 1;
            return; }
        this.page = page;
    }

    public void setSizePerPage(int sizePerPage) {
        if(sizePerPage < 10 || sizePerPage > 100) {
            this.sizePerPage = 10;
            return;
        }
        this.sizePerPage = sizePerPage;
    }
}
