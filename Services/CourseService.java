package com.Project.ELearning.Services;

import com.Project.ELearning.Entities.Course;
import com.Project.ELearning.dtos.CourseDto;
import com.Project.ELearning.dtos.CustomPageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {

    //create Course
    CourseDto create(CourseDto CourseDto);

    //read Course
    CourseDto read(String id);


    CourseDto update(CourseDto dto, String id);

    //delete Course
    void delete(String id);

    //get all
   Page<CourseDto> getAll(Pageable pageable
//            int pageNumber, int pageSize, String sortBy
    );

   List<CourseDto> searchByName(String name);

   List<CourseDto> searchByDesc(String desc);

   List<CourseDto> search(String name,String desc);


}
