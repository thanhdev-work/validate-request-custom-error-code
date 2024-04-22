package com.example.validaterequestcustomerrorcode.annotation;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Validated
public @interface ServiceValidate {
}
