package com.bgosselet.blankApp.students.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCreateForm {
    private String name;
    private Integer gender;

    public String toString(){
        return "{name:" + name + ", gender:" + gender + "}";
    }

}
