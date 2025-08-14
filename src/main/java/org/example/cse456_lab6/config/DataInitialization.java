package org.example.cse456_lab6.config;

import org.example.cse456_lab6.entity.Major;
import org.example.cse456_lab6.entity.Student;
import org.example.cse456_lab6.repo.MajorRepo;
import org.example.cse456_lab6.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitialization implements CommandLineRunner {
    @Autowired
    private MajorService ms;
    @Override
    public void run(String... args) throws Exception {
        Major m = new Major("Software Engineering","Nganh Ky Thuat Phan Mem");
        Major m2 = new Major("Data Communications and Computer Networks","Nganh Mang va Truyen thong du lieu");
        Student s = new Student("s0001", "John Doe", 2000, 97.4);
        Student s2 = new Student("s0002", "Marie", 2000, 87.6);
        Student s3 = new Student("s0003", "Grayson", 2000, 85.5);
        Student s4 = new Student("s0004", "Drake", 2000, 93.2);
        Student s5 = new Student("s0005", "Barbara", 2000, 95.1);

        m.addStudent(s);
        m.addStudent(s2);
        m.addStudent(s3);
        m2.addStudent(s4);
        m2.addStudent(s5);

        ms.saveMajor(m);
        ms.saveMajor(m2);

    }
}
