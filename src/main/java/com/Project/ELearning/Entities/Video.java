package com.Project.ELearning.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private String videoId;

    private  String title;

    @Column(name = "description",length = 1000)
    private  String desc;

    private  String filePath;

    private  String contentType;

    // add your choice of field

    @ManyToOne
    private  Course course;

}
