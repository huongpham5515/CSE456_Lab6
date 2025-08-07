package org.example.cse456_lab6.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/*Neu table co key tu tang thi phai them mot constructor day du tham so nhung bo tham so key di*/
public class Student {
    //Neu day la key tu dong thi khong co trong constructor full tham so va nen dung kieu wrapper
    //class Lang
    private int studentID;
    private String studentName;
    private int yob;
    private double gpa;

}
