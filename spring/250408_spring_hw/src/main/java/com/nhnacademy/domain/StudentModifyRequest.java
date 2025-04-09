package com.nhnacademy.domain;

import jakarta.validation.constraints.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

public class StudentModifyRequest {
    @NotBlank
    String name;

    String password;

    @Email
    String email;

    @Min(0)
    @Max(100)
    int score;

    @Getter
    @NotBlank
    @Size(min = 1, max = 200)
    String comment;
}
