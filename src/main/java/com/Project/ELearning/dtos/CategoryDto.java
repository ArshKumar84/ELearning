package com.Project.ELearning.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.util.Date;


@Data
@ToString
public class CategoryDto {


    private String id;

    @NotBlank(message = "Name is Required!")
    private String name;

    private String desc;

    private Date createdDate;

    private Date lastModified;

    public CategoryDto() {
    }



    public CategoryDto(String name) {
        this.name = name;
    }

}
