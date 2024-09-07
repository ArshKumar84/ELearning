package com.Project.ELearning.controllers;

import com.Project.ELearning.Services.VideoServiceImpl;
import org.springframework.web.bind.annotation.RestController;
import com.Project.ELearning.config.DefaultValues;
import com.Project.ELearning.dtos.VideoDto;
import com.Project.ELearning.dtos.CustomMessage;
import com.Project.ELearning.dtos.CustomPageResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/videos")
public class VideoController {

    private final VideoServiceImpl service;

    public VideoController(VideoServiceImpl service) {
        this.service = service;
    }


    //all categories
    @GetMapping
    CustomPageResponse<VideoDto> getAll(@RequestParam(value = "PageNumber",defaultValue = DefaultValues.PAGE_NUMBER,required = false) int pageNumber,
                                         @RequestParam(value = "PageSize",defaultValue = DefaultValues.PAGE_SIZE,required = false) int pageSize,
                                         @RequestParam(value="SortBy",defaultValue = DefaultValues.SORT_BY_PROPERTY,required = false) String sortBy)
    {
        return service.getAll(pageNumber,pageSize,sortBy);
    }

    //get Video
    @GetMapping("/{id}")
    VideoDto read(@PathVariable String id)
    {
        return service.read(id);
    }

    //create Video
    @PostMapping
    ResponseEntity<VideoDto> create(@Valid @RequestBody VideoDto dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    //update Video
    @PutMapping("/{id}")
    VideoDto update(@RequestBody VideoDto dto,@PathVariable String id)
    {
        return service.update(dto,id);
    }

    //delete Video
    @DeleteMapping("/{id}")
    ResponseEntity<CustomMessage> delete(@PathVariable String id)
    {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CustomMessage("Video Deleted",true));
    }




}

