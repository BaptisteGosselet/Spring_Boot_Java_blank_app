package com.bgosselet.blankApp.students;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

import com.bgosselet.blankApp.students.models.Student;
import com.bgosselet.blankApp.students.models.StudentCreateForm;
import com.bgosselet.blankApp.students.repository.StudentRepository;
import com.bgosselet.blankApp.students.services.StudentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {
    
    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentServiceImpl studentService;

    private Student generateDummyStudent(){
        Student result = new Student();
        result.setId(1);
        result.setName("John");
        result.setGender(1);
        return result;
    }

    @Test
    void findById_shouldReturnStudent(){
        Optional<Student> expected = Optional.of(generateDummyStudent());

        when(studentRepository.findById(anyInt())).thenReturn(expected);
        Optional<Student> result = studentService.findById(1);

        assertThat(result).isNotNull().isEqualTo(expected);
        verify(studentRepository).findById(1);
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    @Disabled
    void findAll_shouldReturnAllStudents(){
        //Excepted Page
        List<Student> studentList = List.of(new Student(), new Student());;
        Page<Student> expectedPage = mock(Page.class);
        when(expectedPage.getContent()).thenReturn(studentList);

        PageRequest pageable = PageRequest.of(0, studentList.size());

        when(studentRepository.findByGender(null, pageable)).thenReturn(expectedPage);
        Page<Student> result = studentService.findAll(null, pageable);

        assertThat(result.getContent()).isEqualTo(studentList);

        verify(studentRepository).findByGender(null, pageable);
        verifyNoMoreInteractions(studentRepository);

    }

    @Test
    @Disabled
    void create_shouldCreateAStudent(){
        Student student = generateDummyStudent();
        when(studentRepository.save(any())).thenReturn(student);

        Student savedStudent = studentService.create(new StudentCreateForm());

        assertThat(savedStudent).usingRecursiveComparison().isEqualTo(student);
        verify(studentRepository).save(any());
        verifyNoMoreInteractions(studentRepository);

    }

}
