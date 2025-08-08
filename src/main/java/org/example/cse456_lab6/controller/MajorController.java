package org.example.cse456_lab6.controller;

import org.example.cse456_lab6.config.InitMajor;
import org.example.cse456_lab6.entity.Major;
import org.example.cse456_lab6.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class MajorController {
    private final InitMajor initMajor;

    public MajorController(InitMajor initMajor){
        this.initMajor = initMajor;
    }
    @GetMapping("/major-list")
    public String showMajorList(Model model){
        List<Major> majors = initMajor.getMajorList();
        model.addAttribute("majors", majors);
        return "major-list";
    }

    @GetMapping("/major/new")
    public String showCreateForm(Model model) {
        model.addAttribute("major", new Major());
        return "create-major";
    }

    @PostMapping("/major/new")
    public String saveNewMajor(@ModelAttribute Major major, RedirectAttributes redirectAttributes) {
        initMajor.getMajorList().add(major);
        redirectAttributes.addFlashAttribute("pmsg", "Major created successfully");
        redirectAttributes.addFlashAttribute("mid", major.getMajorID());
        redirectAttributes.addFlashAttribute("mname", major.getMajorName());
        return "redirect:/major-list";
    }

    @GetMapping("/major/edit/{majorId}")
    public String showEditForm(@PathVariable int majorId, Model model) {
        for (Major m : initMajor.getMajorList()) {
            if (m.getMajorID() == majorId) {
                model.addAttribute("major", m);
                break;
            }
        }
        return "edit-major";
    }

    @PostMapping("/major/edit")
    public String saveEditedMajor(@ModelAttribute("major") Major major, RedirectAttributes redirectAttributes) {
        for (Major m : initMajor.getMajorList()) {
            if (m.getMajorID() == major.getMajorID()) {
                m.setMajorName(major.getMajorName());
                break;
            }
        }

        redirectAttributes.addFlashAttribute("pmsg", "Major updated successfully");
        redirectAttributes.addFlashAttribute("mid", major.getMajorID());
        redirectAttributes.addFlashAttribute("mname", major.getMajorName());
        return "redirect:/major-list";
    }

    @GetMapping("/major/delete/{majorId}")
    public String deleteMajor(@PathVariable int majorId, RedirectAttributes redirectAttributes) {
        boolean removed = initMajor.getMajorList().removeIf(m -> m.getMajorID() == majorId);
        if (removed) {
            redirectAttributes.addFlashAttribute("pmsg", "Major deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("pmsg", "Major not found");
        }
        return "redirect:/major-list";
    }
}
