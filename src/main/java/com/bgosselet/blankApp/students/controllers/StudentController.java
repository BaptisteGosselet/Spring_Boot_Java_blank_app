package com.bgosselet.blankApp.students.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bgosselet.blankApp.exceptions.ResourceNotFoundException;
import com.bgosselet.blankApp.students.models.Student;
import com.bgosselet.blankApp.students.models.StudentCreateForm;
import com.bgosselet.blankApp.students.models.StudentPageDTO;
import com.bgosselet.blankApp.students.models.StudentUpdateForm;

@RequestMapping("/students")
public interface StudentController {
    
    /**
     * Return the student resource matching the given id.
     * @param studentId
     * @return
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<Student> findById(@PathVariable("id") int studentId);

    /**
     * Return the entire list of Student resources.
     * @param gender
     * @param pageable
     * @return
     */
    @GetMapping(value = "", produces = "application/json")
    ResponseEntity<StudentPageDTO> findAll(@RequestParam(name = "gender", required = false) Integer gender, @PageableDefault(value = 5, page = 0, sort = "id") Pageable pageable);

    /**
     * Insert a new Student.
     * 
     * @param createForm
     * @return
     */
    @PostMapping(value = "", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Student> create(@RequestBody StudentCreateForm createForm);

    /**
     * Update a Student.
     * 
     * @param createForm
     * @return
     */
    @PutMapping(value = "", produces = "application/json", consumes = "application/json")
    ResponseEntity<Student> update(@RequestBody StudentUpdateForm updateForm) throws ResourceNotFoundException ;

    /**
     * Delete a Student by its id.
     * 
     * @param studentId
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int studentId) throws ResourceNotFoundException;


}
