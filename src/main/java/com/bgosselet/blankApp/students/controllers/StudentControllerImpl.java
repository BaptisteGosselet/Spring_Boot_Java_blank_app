package com.bgosselet.blankApp.students.controllers;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bgosselet.blankApp.exceptions.ResourceNotFoundException;
import com.bgosselet.blankApp.students.models.Student;
import com.bgosselet.blankApp.students.models.StudentCreateForm;
import com.bgosselet.blankApp.students.models.StudentPageDTO;
import com.bgosselet.blankApp.students.models.StudentUpdateForm;
import com.bgosselet.blankApp.students.services.StudentServiceImpl;

import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudentControllerImpl implements StudentController {

    private static final Logger LOGGER = Logger.getLogger(StudentControllerImpl.class.getName());

    private final StudentServiceImpl studentService;

    @Override
    public ResponseEntity<Student> findById(final int studentId) {
        LOGGER.info("findById - " + studentId);
        Optional<Student> student = studentService.findById(studentId);
        if(!student.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student.get());
    }

    @Override
    public ResponseEntity<StudentPageDTO> findAll(final Integer gender, final Pageable pageable) {
        LOGGER.info("findAll - " + gender + " - " + pageable);
        Page<Student> studentPage = studentService.findAll(gender, pageable);
        StudentPageDTO studentPageDTO = new StudentPageDTO(studentPage);
        return ResponseEntity.ok(studentPageDTO);
    }

    @Override
    public ResponseEntity<Student> create(final StudentCreateForm createForm) {
        LOGGER.info("create - " + createForm);
        Student saved = studentService.create(createForm);
        return new ResponseEntity<Student>(saved, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Student> update(final StudentUpdateForm updateForm) throws ResourceNotFoundException {
        LOGGER.info("update - " + updateForm);
        if(studentService.findById(updateForm.getId()).isEmpty()){
            throw new ResourceNotFoundException();
        }
        Student updated = studentService.update(updateForm);
        return new ResponseEntity<Student>(updated, HttpStatus.OK);
    }

    @Override
    public void delete(final int studentId) throws ResourceNotFoundException {
        LOGGER.info("delete - " + studentId);
        if(studentService.findById(studentId).isEmpty()){
            throw new ResourceNotFoundException();
        }
        studentService.delete(studentId);
    }


}
