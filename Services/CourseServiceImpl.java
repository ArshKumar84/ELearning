package com.Project.ELearning.Services;

import com.Project.ELearning.Entities.Course;
import com.Project.ELearning.Repositores.CourseRepo;
import com.Project.ELearning.dtos.CourseDto;
import com.Project.ELearning.exceptionsHandler.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepo repo;

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepo repo) {
        this.repo = repo;
    }

    @Override
    public CourseDto create(CourseDto CourseDto) {
        Course Course=dtoToEntity(CourseDto);
        Course=repo.save(Course);
        return entityToDto(Course);
    }

    @Override
    public CourseDto read(String id) {
        Course Course=repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course Not found"));
        return entityToDto(Course);
    }
    @Override
    public CourseDto update(CourseDto dto, String id) {
        Course Course = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course Not found"));
        Course.setName(dto.getName());
        Course.setDesc(dto.getDesc());
        Course = repo.save(Course);
        return entityToDto(Course);
    }

    @Override
    public void delete(String id) {
        Course Course = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course Not found"));
        repo.deleteById(id);
    }

    @Override
    public Page<CourseDto> getAll( Pageable pageable)
    {
        Page<Course> coursePage = repo.findAll(pageable);
        List<CourseDto> courseDtos = coursePage
                .stream()
                .map(
                course -> entityToDto(course)
        ).toList();

        return new PageImpl<>(courseDtos,pageable,coursePage.getTotalElements());

    }

    @Override
    public List<CourseDto> searchByName(String name)
    {
//        pattern = "%" + pattern + "%";
//        List<Course> courseList= repo.findCourseByNameLike(pattern);

        List<Course> courseList = repo.findCoursesByNameContaining(name);
        return courseList
                .stream()
                .map(
                        this::entityToDto
                ).toList();
    }

    @Override
    public List<CourseDto> searchByDesc(String desc)
    {

        List<Course> courseList = repo.findCoursesByDescContaining(desc);
        return courseList
                .stream()
                .map(
                        this::entityToDto
                ).toList();
    }

    @Override
    public List<CourseDto> search(String name, String desc) {
        List<Course> courseList = repo.findCoursesByNameContainingAndAndDescContaining(name, desc);

        return courseList
                .stream()
                .map(
                        this::entityToDto
                ).toList();
    }

    Course dtoToEntity(CourseDto dto)
    {
        Course Course = mapper.map(dto, Course.class);
        return Course;
    }

    CourseDto entityToDto(Course Course)
    {
        CourseDto dto = mapper.map(Course,CourseDto.class);
        return dto;
    }

}
