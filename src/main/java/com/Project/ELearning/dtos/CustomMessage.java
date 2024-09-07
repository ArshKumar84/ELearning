package com.Project.ELearning.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class CustomMessage {

    public CustomMessage() {

    }

    public CustomMessage(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    private String message;

    private boolean success;

    private final Date timestamp=new Date();

}
