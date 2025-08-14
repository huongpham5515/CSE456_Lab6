package org.example.cse456_lab6.service;

import org.example.cse456_lab6.entity.Major;
import org.example.cse456_lab6.repo.MajorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorService {
    @Autowired
    private MajorRepo mr;

    public void saveMajor(Major major){
        mr.save(major);
    }

    public List<Major> getMajorList(){
        return mr.findAll();
    }
}
