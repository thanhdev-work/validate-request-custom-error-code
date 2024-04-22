package com.example.validaterequestcustomerrorcode.controller;

import com.example.validaterequestcustomerrorcode.handler.annotation.ServiceValidate;
import com.example.validaterequestcustomerrorcode.common.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("*")
public class SampleController {

    @PostMapping("/sample")
    @ServiceValidate
    public ResponseEntity<?> sampleValidate(@RequestBody @Validated SampleRequest request, BindingResult bindingResult) {
        return BaseResponse.ok("Accepted request");
    }
}
