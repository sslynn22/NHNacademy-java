package com.nhnacademy.repository;

import com.nhnacademy.domain.Student;

public interface StudentRepository {

    Student getStudent(String id);

    boolean matches(String id, String password);

    Student register(Student student);

    void update(String id, Student student);
}
