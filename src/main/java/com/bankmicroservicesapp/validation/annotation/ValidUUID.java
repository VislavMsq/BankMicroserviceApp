package com.bankmicroservicesapp.validation.annotation;

import com.bankmicroservicesapp.exception.ErrorMessage;
import com.bankmicroservicesapp.validation.constrains.ValidUUIDConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Constraint(validatedBy = ValidUUIDConstraint.class)
public @interface ValidUUID {
    String message() default ErrorMessage.INVALID_ID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}