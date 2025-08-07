package org.example.cse456_lab6.controller;

import org.example.cse456_lab6.config.InitStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.cse456_lab6.entity.Student;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
/*xu ly va tra ve ket qua la mot trang web html
* ung voi moi url se co mot ham xu ly tuong ung*/
@Controller
public class StudentController {
    private final InitStudent initStudent;

    @Autowired
    public StudentController(InitStudent initStudent){
        this.initStudent = initStudent;
    }

    //Hien thi trang index
//    @GetMapping("/")
//    public String index(){
//        return "index"; //index.html, khong can go html vi da khai bao suffix
//    }

    //Hien thi danh sach sinh vien
    @GetMapping("/student-list")
    public String showStudentList(Model model){
        /*Ham duoc goi khi user go url localhost:8080/students
        * Sau khi xu ly se tra ve mot trang html
        * Neu kem theo data thi bo trong object Model(attributeName, attributeValue)
        * Thymeleaf se ay data nay thong qua attributeName
        * AttributeValue co the la bat ky obj nao*/
        List<Student> students = initStudent.getStudentList();
        model.addAttribute("students", students);
        return "student-list";
    }

    @GetMapping("student/edit/{studentId}")
    public String showEditForm(@PathVariable int studentId, Model model){
        for(Student student : initStudent.getStudentList()){
            if(student.getStudentID() == studentId){
                model.addAttribute("student", student);
            }
        }
        return "edit-student";
    }
    /*Khi xu ly form
    * Sau khi xu ly va tar ve trang html, nhung url khong thay doi, avn la url cua action
    * form -> neu bam f5 refresh thi se goi lai ham xu ly nay => bat loi resubmission form
    * Trong truong hop them moi data, voi key tu tang thi se bi duplicate du lieu*/
    //Phien ban 2 su dung redirect de tranh bi loi
//    @PostMapping("/student/edit")
//    public String saveStudent(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes){
//        /*Lay thong tin tur form
//        * Hien thi thong tin do len trang result.html*/
//        redirectAttributes.addFlashAttribute("pmsg", "Save student successfully");
//        redirectAttributes.addFlashAttribute("pid", student.getStudentID());
//        redirectAttributes.addFlashAttribute("pname", student.getStudentName());
//        redirectAttributes.addFlashAttribute("pyob", student.getYob());
//        redirectAttributes.addFlashAttribute("pgpa", student.getGpa());
//        return "redirect:/student/edit/result"; //tra ve trang result.html dong thoi doi url thanh /result, chuyen huong url sang /student/edit/result
//        //Nen phai co ham xu ly cho url nay
//        //Tai sao cho nay khong can Model? Model se duoc gui kem theo trang html cho thymeleaf
//    }

    @GetMapping("/student/edit/result")
    public String showResult(Model model){
        return "result";
    }
    //PHIEN Ban 3
    @PostMapping("/student/edit")
    public String saveStudent(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes){
        /*Lay thong tin tur form
         * Hien thi thong tin do len trang result.html*/
        redirectAttributes.addFlashAttribute("pmsg", "Save student successfully");
        redirectAttributes.addFlashAttribute("pid", student.getStudentID());
        redirectAttributes.addFlashAttribute("pname", student.getStudentName());
        redirectAttributes.addFlashAttribute("pyob", student.getYob());
        redirectAttributes.addFlashAttribute("pgpa", student.getGpa());
        return "redirect:/student-list"; //tra ve trang result.html dong thoi doi url thanh /result, chuyen huong url sang /student/edit/result
        //Nen phai co ham xu ly cho url nay
        //Tai sao cho nay khong can Model? Model se duoc gui kem theo trang html cho thymeleaf
    }


}
