package org.example.cse456_lab6.service;

import org.example.cse456_lab6.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.cse456_lab6.entity.Student;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo sr;

    public void saveStudent(Student s){
        sr.save(s);
    }

    public List<Student> getStudentList(){
        return sr.findAll();
    }

    public void deleteStudentById(String studentId){
        sr.deleteById(studentId);
    }

    public List<Student> searchStudentByName(String keyword){
        return sr.searchAllByStudentNameContainingIgnoreCase(keyword);
    }

    public boolean checkStudentExistById(String id){
        return sr.existsById(id);
    }
}
