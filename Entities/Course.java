package com.Project.ELearning.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private String id;

    private String name;
    @Column(name = "Description")
    private String desc;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> categoryList;

    public Course(String id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\''+
//                +
//                ", category=" + category +
                '}'
                ;
    }
}
