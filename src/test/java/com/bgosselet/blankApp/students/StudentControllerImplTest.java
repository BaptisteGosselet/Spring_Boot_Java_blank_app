package com.bgosselet.blankApp.students;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bgosselet.blankApp.exceptions.ResourceNotFoundException;
import com.bgosselet.blankApp.students.controllers.StudentControllerImpl;
import com.bgosselet.blankApp.students.models.Student;
import com.bgosselet.blankApp.students.models.StudentCreateForm;
import com.bgosselet.blankApp.students.models.StudentPageDTO;
import com.bgosselet.blankApp.students.services.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentControllerImplTest {
    
    @Mock
    StudentService studentService;

    @InjectMocks
    StudentControllerImpl studentController;


    private Student generateDummyStudent(){
        Student result = new Student();
        result.setId(1);
        result.setName("John");
        result.setGender(1);
        return result;
    }

    @Test
    @Disabled
    void findById_shouldReturnStudent(){
        Student expected = generateDummyStudent();

        when(studentService.findById(anyInt())).thenReturn(Optional.of(expected));
        ResponseEntity<Student> result = studentController.findById(1);

        assertThat(result.getStatusCode().equals(HttpStatus.OK));
        assertThat(result.getBody()).isEqualTo(expected);

        verify(studentService).findById(1);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    @Disabled
    void findById_shouldThrowExceptionWhenNotFound(){
        Optional<Student> expected = Optional.empty();
        when(studentService.findById(anyInt())).thenReturn(expected);
        ResponseEntity<Student> result = studentController.findById(1);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @Disabled
    void findAll_shouldReturnAllStudents(){
        List<Student> studentList = List.of(new Student(), new Student());
        int expectedPageNumber = 0;

        //Expected Page DTO
        StudentPageDTO expectedStudentPageDTO = new StudentPageDTO(new PageImpl<>(studentList));
        expectedStudentPageDTO.setContent(studentList);
        expectedStudentPageDTO.setPageNumber(expectedPageNumber);

        //Expected Service Page
        Page<Student> expectedPage = mock(Page.class);
        when(expectedPage.getContent()).thenReturn(studentList);
        when(expectedPage.getNumber()).thenReturn(expectedPageNumber);
        when(studentService.findAll(null, PageRequest.of(0, 10))).thenReturn(expectedPage);

        ResponseEntity<StudentPageDTO> result = studentController.findAll(null, PageRequest.of(0, 10));

        assertThat(result.getStatusCode().equals(HttpStatus.OK));
        assertThat(result.getBody().getContent()).isEqualTo(expectedStudentPageDTO.getContent());
        assertThat(result.getBody().getPageNumber()).isEqualTo(expectedStudentPageDTO.getPageNumber());

        verify(studentService).findAll(null, PageRequest.of(0, 10));
        verifyNoMoreInteractions(studentService);
    }

    @Test
    @Disabled
    void create_shouldCreateAStudent(){
        Student student = generateDummyStudent();
        when(studentService.findById(anyInt())).thenReturn(Optional.empty());
        when(studentService.create(any())).thenReturn(student);
        ResponseEntity<Student> result = studentController.create(new StudentCreateForm());

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isEqualTo(student);

        verify(studentService.create(any()));
        verifyNoMoreInteractions(studentService);
    }

    @Test
    @Disabled
    void update_shouldUpdateAnEmployee() throws ResourceNotFoundException{
        Student student = generateDummyStudent();

        when(studentService.findById(anyInt())).thenReturn(Optional.of(student));
        when(studentService.update(any())).thenReturn(student);
        ResponseEntity<Student> result = studentController.update(any());

        assertThat(result.getStatusCode() == HttpStatus.OK);
        assertThat(result.getBody()).isNotNull().isEqualTo(student);

        verify(studentService).update(any());
        verifyNoMoreInteractions(studentService);
    }

    @Test
    @Disabled
    void update_shouldThrowExceptionWhenNotFound(){
        when(studentService.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> studentController.update(any()));
    }

    @Test
    @Disabled
    void delete_shouldDeleteAStudent() throws ResourceNotFoundException{
        Student student = generateDummyStudent();
        int id = 1;

        when(studentService.findById(anyInt())).thenReturn(Optional.of(student));
        studentController.delete(id);

        verify(studentService).delete(id);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    @Disabled
    void delete_shouldThrowExceptionWhenNotFound(){
        when(studentService.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> studentController.delete(1));
    }


}
