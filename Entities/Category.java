package com.Project.ELearning.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Entity
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Category {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private String id;

    private String name;

    @Column(name = "Description")
    private String desc;

    @Column(name = "Posted On")
    @CreatedDate
    private Date createdDate;

    @Column(name = "LastModifiedDate")
    @LastModifiedDate
    private Date lastModified;

    @JsonIgnore
    @ManyToMany(mappedBy = "categoryList",fetch = FetchType.EAGER)
    private List<Course> courseList;

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }
//
//    @Override
//    public String toString() {
//        return "Category{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", courseList=" + courseList +
//                '}';
//    }
}
