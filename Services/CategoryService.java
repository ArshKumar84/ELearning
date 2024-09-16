package com.Project.ELearning.Services;

import com.Project.ELearning.Entities.Category;
import com.Project.ELearning.dtos.CategoryDto;
import com.Project.ELearning.dtos.CourseDto;
import com.Project.ELearning.dtos.CustomPageResponse;
import org.springframework.data.domain.Page;

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
    Page<Category> getAll(int pageNumber, int pageSize, String sortBy);
}
