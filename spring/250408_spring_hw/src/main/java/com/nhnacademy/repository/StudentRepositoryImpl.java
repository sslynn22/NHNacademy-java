package com.nhnacademy.repository;

import com.nhnacademy.domain.Student;
import com.nhnacademy.exception.StudentAlreadyExistsException;
import com.nhnacademy.exception.StudentNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final Map<String, Student> studentMap = new HashMap<>();

    @Override
    public Student getStudent(String id) {
        return studentMap.get(id);
    }

    @Override
    public boolean matches(String id, String password) {
        Student student = getStudent(id);
        return !Objects.isNull(student) && student.getPassword().equals(password);
    }

    @Override
    public Student register(Student student) {
        if (studentMap.containsKey(student.getId())) {
            throw new StudentAlreadyExistsException();
        }
        studentMap.put(student.getId(), student);
        return student;
    }

    @Override
    public void update(String id, Student student) {
        if (studentMap.containsKey(id)) {
            studentMap.put(id, student);
        } else {
            throw new StudentNotFoundException();
        }
    }
}