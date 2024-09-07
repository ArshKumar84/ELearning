package com.Project.ELearning.Services;

import com.Project.ELearning.dtos.CategoryDto;
import com.Project.ELearning.dtos.CourseDto;
import com.Project.ELearning.dtos.CustomPageResponse;

import java.util.List;

public interface CategoryService {

    //create category
    CategoryDto create(CategoryDto categoryDto);

    //read category
    CategoryDto read(String id);

    //update category
    CategoryDto update(CategoryDto dto, String id);

    //delete category
    void delete(String id);

    //get all
    CustomPageResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy);
}
