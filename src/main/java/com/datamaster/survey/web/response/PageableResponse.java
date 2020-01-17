package com.datamaster.survey.web.response;

import lombok.ToString;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.List;

@ToString(callSuper = true)
public class PageableResponse<T> extends Response {

    private Long total;
    private Pageable page;
    private List<T> result;

    public PageableResponse(List<T> result, Pageable page, Long total) {
        super(true, null, ResponseCode.OK, HttpStatus.OK);
        this.page = page;
        this.result = result;
        this.total = total;
    }

    public Long getTotal() {
        return total;
    }

    public Pageable getPage() {
        return page;
    }

    public List<T> getResult() {
        return result;
    }
}