package org.example.cse456_lab6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_major")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int majorID;
    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String majorName;
    @Column(nullable = false)
    private String description;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "major")
    private List<Student> students = new ArrayList<>();

    public Major(String majorName, String description) {
        this.majorName = majorName;
        this.description = description;
    }

    public void addStudent(Student s){
        students.add(s);
        s.setMajor(this);
    }

}
