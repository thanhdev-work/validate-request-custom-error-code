package com.example.validaterequestcustomerrorcode.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SampleRequest {

    @NotNull(message = "400013")
    private String token;

    @NotNull(message = "404800")
    private String user;
}
