package org.example.cse456_lab6.config;

import jakarta.annotation.PostConstruct;
import org.example.cse456_lab6.entity.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component //giao cho spring quan ly
public class InitStudent {
    private List<Student> students;

    //Ham khoi tao danh sach hard code
    @PostConstruct
    public void init() {
        students = new ArrayList<>();
        students.add(new Student(1,"John Doe", 20, 3.8));
        students.add(new Student(2,"Jane Smith", 22, 3.9));
        students.add(new Student(3,"Alice Johnson", 21, 3.7));
        students.add(new Student(4, "Bob Brown", 23, 3.6));
        students.add(new Student(5,"Charlie Davis", 24, 3.5));
        System.out.println("Students initialized successfully.");
    }

    public List<Student> getStudentList() {
        return students;
    }
}
