package org.example.cse456_lab6.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_student")
/*Neu table co key tu tang thi phai them mot constructor day du tham so nhung bo tham so key di*/
public class Student {
    //Neu day la key tu dong thi khong co trong constructor full tham so va nen dung kieu wrapper
    //class Lang
    @Id
    @Column(nullable = false, columnDefinition = "CHAR(5)")
    @Size(min = 5, max = 5, message = "ID for student must contains 5 letters")
    @NotBlank(message = "ID must not be blank")
    private String studentID;

    @Column(name = "name", nullable = false, columnDefinition = "CHAR(100)")
    @NotNull(message = "Name must not be null")
    @Size(min = 5, max = 100, message = "Length of name should be between 5 to 100")
    @Pattern(
            regexp = "^([\\p{Lu}][\\p{L}\\p{M}'-]*)(\\s[\\p{Lu}][\\p{L}\\p{M}'-]*)*$",
            message = "Each word must start with a capital letter"
    )
    private String studentName;
    @Min(value = 2000, message = "You must be from 2000 onwards")
    @Max(value = 2007, message = "You must be from 2007 backwards")
    @NotNull(message = "This must not be null")
    @Column(nullable = false, name = "year_of_birth")
    private int yob;

    @Column(name = "gpa")
    @DecimalMin(value = "0.0", message = "GPA must be between 0 and 100")
    @DecimalMax(value = "100", message = "GPA must be between 0 and 100")
    private double gpa;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "major_id")
    private Major major;

    public Student(String studentID, String studentName, int yob, double gpa) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.yob = yob;
        this.gpa = gpa;
    }
}
