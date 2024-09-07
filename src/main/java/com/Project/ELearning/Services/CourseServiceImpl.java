package com.Project.ELearning.Services;

import com.Project.ELearning.Entities.Course;
import com.Project.ELearning.Repositores.CourseRepo;
import com.Project.ELearning.dtos.CourseDto;
import com.Project.ELearning.dtos.CustomPageResponse;
import com.Project.ELearning.exceptionsHandler.InvalidParametersException;
import com.Project.ELearning.exceptionsHandler.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public CustomPageResponse<CourseDto> getAll(int pageNumber, int pageSize, String sortBy)
    {
        Sort sort=Sort.by(sortBy).ascending();
        PageRequest request = PageRequest.of(pageNumber, pageSize,sort);
        Page<Course> CoursePage;
        try {
            CoursePage = repo.findAll(request);
        }
        catch (Exception ex)
        {
            throw new InvalidParametersException();
        }

        List<Course> CourseList=CoursePage.stream().toList();
        List<CourseDto> CourseDtoList=CourseList.stream().map(this::entityToDto).toList();
        return new CustomPageResponse<>(
                pageNumber,
                pageSize,
                CourseDtoList,
                CoursePage.isLast()
        );


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
