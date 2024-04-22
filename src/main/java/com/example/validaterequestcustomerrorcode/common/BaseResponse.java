package com.example.validaterequestcustomerrorcode.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class BaseResponse<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pagination pagination;
    private T data;
    @JsonProperty("response_time")
    private long responseTime;

    public static <T> ResponseEntity<BaseResponse<T>> ok(T data) {
        BaseResponse<T> response = new BaseResponse<>();

        response.setPagination(null);
        response.setData(data);
        response.setResponseTime(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static <T> ResponseEntity<BaseResponse<T>> ok(T data, Pagination pagination) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setPagination(pagination);
        response.setData(data);
        response.setResponseTime(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static <T> ResponseEntity<BaseResponse<T>> ok(T data, int page, int limit, int size) {
        BaseResponse<T> response = new BaseResponse<>();
        Pagination pagination = new Pagination(page, limit, size);
        response.setPagination(pagination);
        response.setData(data);
        response.setResponseTime(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static <T> ResponseEntity<BaseResponse<T>> badReq(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setPagination(null);
        response.setData(data);
        response.setResponseTime(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
