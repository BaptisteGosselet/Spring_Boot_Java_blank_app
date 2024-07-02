package com.bgosselet.blankApp.students.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bgosselet.blankApp.exceptions.ResourceNotFoundException;
import com.bgosselet.blankApp.students.models.Student;
import com.bgosselet.blankApp.students.models.StudentCreateForm;
import com.bgosselet.blankApp.students.models.StudentUpdateForm;

public interface StudentService {
    
    /**
     * Return the student resource matching the given id.
     *
     * @param studentId
     * @return
     */
    Optional<Student> findById(final int studentId);

    /**
     * Return the entire list of student resources.
     * @param gender filter by gender
     * @param pageable
     * @return
     */
    Page<Student> findAll(final Integer gender, final Pageable pageable);


    /**
     * Create a Student
     * 
     * @param createForm
     * @return
     */
    Student create(final StudentCreateForm createForm);

    /**
     * Update a Student.
     * 
     * @param updateForm
     * @return
     */
    Student update(final StudentUpdateForm updateForm) throws ResourceNotFoundException;

    /**
     * Delete a Student by its id.
     * 
     * @param studentId
     */
    void delete(final int studentId);


}
