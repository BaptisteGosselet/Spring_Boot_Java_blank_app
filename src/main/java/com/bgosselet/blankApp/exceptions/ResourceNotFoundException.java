package com.bgosselet.blankApp.exceptions;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(){
        super("Resource Not Found Exception.");
    }
    public ResourceNotFoundException(String message){
        super(message);
    }

}
