package com.ftn.sbnz.service.core.validation.validator;

import com.ftn.sbnz.service.core.validation.annotation.Password;
import org.passay.PasswordData;
import org.passay.RuleResult;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    private String field;
    private final org.passay.PasswordValidator passwordValidator;

    public PasswordValidator(org.passay.PasswordValidator passwordValidator) {
        this.passwordValidator = passwordValidator;
    }

    @Override
    public void initialize(Password constraintAnnotation) {
        field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            setUpContext(context, field + " must not be null.");
            return false;
        }

        PasswordData passwordData = new PasswordData(password);
        RuleResult result = passwordValidator.validate(passwordData);

        if (!result.isValid()) {
            List<String> messages = passwordValidator.getMessages(result);

            String fullMessage = String.join("|", messages);
            if (!field.equals("Password")) {
                fullMessage = fullMessage.replace("Password", field);
            }

            setUpContext(context, fullMessage);
            return false;
        }

        return true;
    }

    private void setUpContext(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
