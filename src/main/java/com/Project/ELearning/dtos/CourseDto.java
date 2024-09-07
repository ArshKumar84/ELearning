package com.Project.ELearning.dtos;

import com.Project.ELearning.Entities.Category;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class CourseDto {

    private String id;

    @NotBlank(message = "Name is Required")
    private String name;

    @NotBlank(message = "Description is Required")
    private String desc;

    public CourseDto() {
    }

    CourseDto(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }




}
