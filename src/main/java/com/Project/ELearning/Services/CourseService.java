package com.Project.ELearning.Services;

import com.Project.ELearning.dtos.CourseDto;
import com.Project.ELearning.dtos.CustomPageResponse;

public interface CourseService {

    //create Course
    CourseDto create(CourseDto CourseDto);

    //read Course
    CourseDto read(String id);


    CourseDto update(CourseDto dto, String id);

    //delete Course
    void delete(String id);

    //get all
    CustomPageResponse<CourseDto> getAll(int pageNumber, int pageSize, String sortBy);


}
