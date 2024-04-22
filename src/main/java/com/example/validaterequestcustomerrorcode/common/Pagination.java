package com.example.validaterequestcustomerrorcode.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@JsonIgnoreProperties({"sort", "offset", "pageSize", "pageNumber", "paged", "unpaged"})
public class Pagination extends PageRequest {
    private static final long serialVersionUID = 1L;

    @JsonProperty("page")
    private int page;

    @JsonProperty("limit")
    private int limit;

    @JsonProperty("totalRecords")
    private long totalRecords;

    public Pagination(int page, int size) {
        super(page, size, Sort.unsorted());
        this.page = page;
        this.limit = size;
    }

    public Pagination(int page, int limit, int size) {
        super(page, limit, Sort.unsorted());
        this.page = page;
        this.limit = limit;
        this.totalRecords = size;
    }

    public Pagination(int page, int limit, int size, Sort sort) {
        super(page, limit, sort);
        this.page = page;
        this.limit = limit;
        this.totalRecords = size;
    }

    public Pagination(int page, int limit, Sort sort) {
        super(page, limit, sort);
        this.page = page;
        this.limit = limit;
    }

    public void setTotalRecords(Page pageInfo) {
        this.totalRecords = pageInfo.getTotalElements();
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }
}
