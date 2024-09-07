package com.Project.ELearning.Repositores;

import com.Project.ELearning.Entities.Category;
import com.Project.ELearning.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course,String> {
//    Optional<List<Course>> findCourseByCategory(Category category);
}
