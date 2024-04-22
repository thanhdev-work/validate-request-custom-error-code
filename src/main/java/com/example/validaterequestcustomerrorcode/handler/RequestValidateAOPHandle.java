package com.example.validaterequestcustomerrorcode.handler;

import com.example.validaterequestcustomerrorcode.annotation.ServiceValidate;
import com.example.validaterequestcustomerrorcode.common.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The Class PaymentValidateAOPHandle.
 */
@Aspect
@Component
@Order(value = 3)
@PropertySource(value = "classpath:messages/message.properties", encoding = "UTF-8",
        name = "messageCode")
public class ServiceProcessingValidateAOPHandle {
    @Autowired
    private Environment env;

    @Around("execution(* *(..)) && @annotation(hrmsValidateAnnotation)")
    public Object hrmsValidateAnnotation(
            ProceedingJoinPoint point, ServiceValidate hrmsValidateAnnotation)
            throws Throwable {
        // Get dataRequest
        BindingResult bindingResult = (BindingResult) point.getArgs()[1];
        AbstractEnvironment ae = (AbstractEnvironment) env;
        org.springframework.core.env.PropertySource dfsErrorSource =
                ae.getPropertySources().get("messageCode");
        Properties props = new Properties();
        if (dfsErrorSource != null) {
            props = (Properties) dfsErrorSource.getSource();
        }
        // Validate data
        if (bindingResult.hasErrors()) {
            List<InvalidProperties> invalidProperties = new ArrayList<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                String message = fieldError.getDefaultMessage();
                if (NumberUtils.isDigits(message)) {
                    message = props.getProperty(message);
                }
                invalidProperties.add(new InvalidProperties(fieldError.getField(), message));
            }
            return BaseResponse.badReq(invalidProperties);
        } else {
            return point.proceed();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    static class InvalidProperties {
        private String property;
        private String message;
    }
}
