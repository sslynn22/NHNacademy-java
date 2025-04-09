package com.nhnacademy.validator;

import com.nhnacademy.domain.StudentRegisterRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class StudentRegisterRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return StudentRegisterRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "content must not be empty");
        StudentRegisterRequest reqeust  = (StudentRegisterRequest) target;
        String content = reqeust.getComment();
        if (content.length() > 200) {
            errors.rejectValue("content", "content length must be less than 200");
        }
    }
}
