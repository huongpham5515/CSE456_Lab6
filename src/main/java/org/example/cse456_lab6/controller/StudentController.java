package org.example.cse456_lab6.controller;

import jakarta.validation.Valid;
import org.example.cse456_lab6.service.MajorService;
import org.example.cse456_lab6.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.example.cse456_lab6.entity.Student;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
/*xu ly va tra ve ket qua la mot trang web html
* ung voi moi url se co mot ham xu ly tuong ung*/
@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private MajorService majorService;

    //Hien thi trang index
//    @GetMapping("/")
//    public String index(){
//        return "index"; //index.html, khong can go html vi da khai bao suffix
//    }

    //Hien thi danh sach sinh vien
    @GetMapping("/students")
    public String showStudentList(Model model, @RequestParam(value = "keyword", defaultValue = "") String keyword){
        /*Ham duoc goi khi user go url localhost:8080/students
        * Sau khi xu ly se tra ve mot trang html
        * Neu kem theo data thi bo trong object Model(attributeName, attributeValue)
        * Thymeleaf se ay data nay thong qua attributeName
        * AttributeValue co the la bat ky obj nao*/
        List<Student> students = new ArrayList<>();
        if(keyword.isBlank()){
            students = studentService.getStudentList();
        }else{
            students = studentService.searchStudentByName(keyword);
        }
        model.addAttribute("students", students);
        return "student-list";
    }
    @GetMapping("/student/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("majors", majorService.getMajorList());
        model.addAttribute("formName", "create");
        return "student-form";
    }


//    @PostMapping("/student/form")
//    public String createStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
//        studentService.getStudentList().add(student);
//        redirectAttributes.addFlashAttribute("pmsg", "Save student successfully");
//        redirectAttributes.addFlashAttribute("pid", student.getStudentID());
//        redirectAttributes.addFlashAttribute("pname", student.getStudentName());
//        redirectAttributes.addFlashAttribute("pyob", student.getYob());
//        redirectAttributes.addFlashAttribute("pgpa", student.getGpa());
//        return "redirect:/student-list";
//    }

    @GetMapping("student/edit/{studentId}")
    public String showEditForm(@PathVariable String studentId, Model model){
        for(Student student : studentService.getStudentList()){
            if(student.getStudentID().equals(studentId)){
                model.addAttribute("student", student);
            }
        }
        model.addAttribute("formName", "edit");
        model.addAttribute("majors", majorService.getMajorList());
        return "student-form";
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

//    @GetMapping("/student/edit/result")
//    public String showResult(Model model){
//        return "result";
//    }
    //PHIEN Ban 3
    @PostMapping("/student/form")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, RedirectAttributes redirectAttributes,
                              Model model, @RequestParam("formName") String mode){
        /*Lay thong tin tur form
         * Hien thi thong tin do len trang result.html*/
        if(result.hasErrors()){
            model.addAttribute("formName", mode);
            model.addAttribute("majors", majorService.getMajorList());
            return"student-form";
        }

        if(mode.equals("create")){
            if(studentService.checkStudentExistById(student.getStudentID())){
                model.addAttribute("formName", mode);
                model.addAttribute("majors", majorService.getMajorList());
                model.addAttribute("duplicate", "ID already exist");
                return"student-form";
            }
        }
        Student existing = null;
        for (Student s : studentService.getStudentList()) {
            if (s.getStudentID().equals(student.getStudentID())) {
                existing = s;
                break;
            }
        }
        if(existing == null){
            studentService.saveStudent(student);
            redirectAttributes.addFlashAttribute("pmsg", "Save student successfully");
            redirectAttributes.addFlashAttribute("pid", student.getStudentID());
            redirectAttributes.addFlashAttribute("pname", student.getStudentName());
            redirectAttributes.addFlashAttribute("pyob", student.getYob());
            redirectAttributes.addFlashAttribute("pgpa", student.getGpa());
        }else{
            existing.setStudentName(student.getStudentName());
            existing.setYob(student.getYob());
            existing.setGpa(student.getGpa());
            existing.setMajor(student.getMajor());
            studentService.saveStudent(existing);
            redirectAttributes.addFlashAttribute("pmsg", "Update student successfully");
        }
        return "redirect:/students"; //tra ve trang result.html dong thoi doi url thanh /result, chuyen huong url sang /student/edit/result
        //Nen phai co ham xu ly cho url nay
        //Tai sao cho nay khong can Model? Model se duoc gui kem theo trang html cho thymeleaf
    }

    @GetMapping("/student/delete/{studentId}")
    public String deleteStudent(@PathVariable String studentId, RedirectAttributes redirectAttributes) {
        studentService.deleteStudentById(studentId);
        redirectAttributes.addFlashAttribute("pmsg", "Student deleted successfully");

        return "redirect:/students";
    }


}
