package com.Project.ELearning.controllers;

import com.Project.ELearning.Entities.Course;
import com.Project.ELearning.Services.CategoryServiceImpl;
import com.Project.ELearning.Services.CourseServiceImpl;
import com.Project.ELearning.config.DefaultValues;
import com.Project.ELearning.dtos.CategoryDto;
import com.Project.ELearning.dtos.CourseDto;
import com.Project.ELearning.dtos.CustomMessage;
import com.Project.ELearning.dtos.CustomPageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseServiceImpl service;

    public CourseController(CourseServiceImpl service) {
        this.service = service;
    }


    //all categories
    @GetMapping
    Page<CourseDto> getAll(
            Pageable pageable //Implicitly RequestParameters are converted to Pageable object, So, there is no need of @RequestParam annotation
            //Feature name : Automatic Pageable Resolution
            //Spring Data automatically binds the query parameters to the Pageable parameter using a HandlerMethodArgumentResolver called "PageableHandlerMethodArgumentResolver"
    )
    {
        return service.getAll( pageable );
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


//    @GetMapping("/search")
//    ResponseEntity<List<CourseDto>> search(@RequestParam String name)
//    {
//        List<CourseDto> courseDtoList=service.searchByName(name);
//        return ResponseEntity.ok(courseDtoList);
//    }

    @GetMapping("/search")
    ResponseEntity<List<CourseDto>> search(@RequestParam(required = false) String name,@RequestParam(required = false) String desc)
    {
        List<CourseDto> courseDtoList;

        if(desc!=null && name!=null ){
             courseDtoList = service.search(name, desc);
        }
        else if(name!=null)
        {
             courseDtoList=service.searchByName(name);
        }
        else
        {
            courseDtoList=service.searchByDesc(desc);
        }
        return ResponseEntity.ok(courseDtoList);
    }


}
