package com.Project.ELearning.Repositores;

import com.Project.ELearning.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course,String> {
//        List<Course> findCourseByNameLike(String pattern);
        List<Course> findCoursesByNameContaining(String name);

        List<Course> findCoursesByDescContaining(String desc);

        List<Course> findCoursesByNameContainingAndAndDescContaining(String name, String desc);
}
