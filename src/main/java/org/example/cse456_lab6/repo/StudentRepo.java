package org.example.cse456_lab6.repo;

import org.example.cse456_lab6.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, String> {
    List<Student> searchAllByStudentNameContainingIgnoreCase(String name);
}
