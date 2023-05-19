package com.ftn.sbnz.service.core.validation.annotation;

import com.ftn.sbnz.service.core.validation.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    String field() default "Password";

    String message() default "{default.invalid.password.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

