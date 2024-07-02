package com.bgosselet.blankApp.students.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentUpdateForm {
    private Integer id;
    private String name;
    private Integer gender;

    public String toString(){
        return "{id:" + id + ", name:" + name + ", gender:" + gender + "}";
    }

}