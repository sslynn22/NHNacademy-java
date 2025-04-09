package com.nhnacademy.validator;

import com.nhnacademy.domain.StudentModifyRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StudentModifyRequestValidator implements Validator {
    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return StudentModifyRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "content must not be empty");
        StudentModifyRequest request = (StudentModifyRequest) target;
        String content = request.getComment();
        if (content.length() > 200) {
            errors.rejectValue("content", "", "content max length is 200");
        }

    }
}
