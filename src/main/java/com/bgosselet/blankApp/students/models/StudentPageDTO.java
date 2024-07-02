package com.bgosselet.blankApp.students.models;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentPageDTO {

    private static final Logger LOGGER = Logger.getLogger(StudentPageDTO.class.getName());
    
    private List<Student> content;
    private int pageNumber;
    private int totalPage;
    private long totalElements;

    public StudentPageDTO(Page<Student> studentPage){
        LOGGER.info("StudentPageDTO - " + studentPage);
        content = studentPage.getContent();
        pageNumber = studentPage.getNumber();
        totalPage = studentPage.getTotalPages();
        totalElements = studentPage.getTotalElements();
    }

}
