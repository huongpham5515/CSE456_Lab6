package org.example.cse456_lab6.repo;

import org.example.cse456_lab6.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorRepo extends JpaRepository<Major, Integer> {
}
