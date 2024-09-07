package com.Project.ELearning.controllers;

import com.Project.ELearning.Services.CategoryServiceImpl;
import com.Project.ELearning.config.DefaultValues;
import com.Project.ELearning.dtos.CategoryDto;
import com.Project.ELearning.dtos.CustomMessage;
import com.Project.ELearning.dtos.CustomPageResponse;
import com.Project.ELearning.exceptionsHandler.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@ResponseBody
public class CategoryController {

    private final CategoryServiceImpl service;

    public CategoryController(CategoryServiceImpl service) {
        this.service = service;
    }


    //all categories
    @GetMapping
    CustomPageResponse<CategoryDto> getAll(@RequestParam(value = "PageNumber",defaultValue = DefaultValues.PAGE_NUMBER,required = false) int pageNumber,
                                           @RequestParam(value = "PageSize",defaultValue = DefaultValues.PAGE_SIZE,required = false) int pageSize,
                                           @RequestParam(value="SortBy",defaultValue = DefaultValues.SORT_BY_PROPERTY,required = false) String sortBy)
    {
        return service.getAll(pageNumber,pageSize,sortBy);
    }

    //get category
    @GetMapping("/{id}")
    CategoryDto read(@PathVariable String id)
    {
        return service.read(id);
    }

    //create category
    @PostMapping
    ResponseEntity<CategoryDto> create( @Valid @RequestBody CategoryDto dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    //update category
    @PutMapping("/{id}")
    CategoryDto update(@RequestBody CategoryDto dto,@PathVariable String id)
    {
        return service.update(dto,id);
    }

    //delete category
    @DeleteMapping("/{id}")
    ResponseEntity<CustomMessage> delete(@PathVariable String id)
    {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CustomMessage("Category Deleted",true));
    }


}
