package org.example.cse456_lab6.config;

import jakarta.annotation.PostConstruct;
import org.example.cse456_lab6.entity.Major;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class InitMajor {
    private List<Major> majors;
    @PostConstruct
    public void init(){
        majors = new ArrayList<>();
        majors.add(new Major(1,"Computer Science"));
        majors.add(new Major(2,"Mathematics"));
        majors.add(new Major(3,"Engineering"));
        System.out.println("Majors initialized successfully.");
    }

    public List<Major> getMajorList(){
        return majors;
    }
}
