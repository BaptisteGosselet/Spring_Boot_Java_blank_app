package com.bgosselet.blankApp.students.services;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bgosselet.blankApp.exceptions.ResourceNotFoundException;
import com.bgosselet.blankApp.students.models.Student;
import com.bgosselet.blankApp.students.models.StudentCreateForm;
import com.bgosselet.blankApp.students.models.StudentUpdateForm;
import com.bgosselet.blankApp.students.repository.StudentRepository;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = Logger.getLogger(StudentServiceImpl.class.getName());

    private final StudentRepository studentRepository;

    @Override
    public Optional<Student> findById(final int studentId) {
        LOGGER.info("findById - " + studentId);
        return studentRepository.findById(studentId);
    }

    @Override
    public Page<Student> findAll(final Integer gender, final Pageable pageable) {
        LOGGER.info("findAll - " + gender + " - " + pageable);
        
        if(gender == null){
            return studentRepository.findAll(pageable);
        }
        return studentRepository.findByGender(gender, pageable);
    }

    @Override
    public Student create(final StudentCreateForm createForm) {
        LOGGER.info("create - " + createForm);
        Student student = new Student();
        student.setName(createForm.getName());
        student.setGender(createForm.getGender());
        return studentRepository.save(student);
    }

    @Override
    public Student update(final StudentUpdateForm updateForm) throws ResourceNotFoundException {
        LOGGER.info("update - " + updateForm);
        Optional<Student> optionalStudent = studentRepository.findById(updateForm.getId());
        if(!optionalStudent.isPresent()){
            throw new ResourceNotFoundException();
        }
        Student student = optionalStudent.get();

        if(StringUtils.isNotBlank(updateForm.getName())){
            student.setName(updateForm.getName());
        }

        if(updateForm.getGender() != null){
            student.setGender(updateForm.getGender());
        }

        return studentRepository.save(student);
    }

    @Override
    public void delete(final int studentId) {
        LOGGER.info("delete - " + studentId);
        studentRepository.deleteById(studentId);
    }
    

}
