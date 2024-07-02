package com.bgosselet.blankApp.students.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bgosselet.blankApp.students.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>  {
    
    Page<Student> findByGender(Integer gender, Pageable pageable);

}
