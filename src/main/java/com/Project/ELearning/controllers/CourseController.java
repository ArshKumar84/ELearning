package com.Project.ELearning.controllers;

import com.Project.ELearning.Services.CategoryServiceImpl;
import com.Project.ELearning.Services.CourseServiceImpl;
import com.Project.ELearning.config.DefaultValues;
import com.Project.ELearning.dtos.CategoryDto;
import com.Project.ELearning.dtos.CourseDto;
import com.Project.ELearning.dtos.CustomMessage;
import com.Project.ELearning.dtos.CustomPageResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseServiceImpl service;

    public CourseController(CourseServiceImpl service) {
        this.service = service;
    }


    //all categories
    @GetMapping
    CustomPageResponse<CourseDto> getAll(@RequestParam(value = "PageNumber",defaultValue = DefaultValues.PAGE_NUMBER,required = false) int pageNumber,
                                         @RequestParam(value = "PageSize",defaultValue = DefaultValues.PAGE_SIZE,required = false) int pageSize,
                                         @RequestParam(value="SortBy",defaultValue = DefaultValues.SORT_BY_PROPERTY,required = false) String sortBy)
    {
        return service.getAll(pageNumber,pageSize,sortBy);
    }

    //get Course
    @GetMapping("/{id}")
    CourseDto read(@PathVariable String id)
    {
        return service.read(id);
    }

    //create Course
    @PostMapping
    ResponseEntity<CourseDto> create(@Valid @RequestBody CourseDto dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    //update Course
    @PutMapping("/{id}")
    CourseDto update(@RequestBody CourseDto dto,@PathVariable String id)
    {
        return service.update(dto,id);
    }

    //delete Course
    @DeleteMapping("/{id}")
    ResponseEntity<CustomMessage> delete(@PathVariable String id)
    {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CustomMessage("Course Deleted",true));
    }




}
