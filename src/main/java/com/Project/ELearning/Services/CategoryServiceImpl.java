package com.Project.ELearning.Services;

import com.Project.ELearning.Entities.Category;
import com.Project.ELearning.Repositores.CategoryRepo;
import com.Project.ELearning.dtos.CategoryDto;
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
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo repo;

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepo repo) {
        this.repo = repo;
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category category=dtoToEntity(categoryDto);
        category=repo.save(category);
        return entityToDto(category);
    }

    @Override
    public CategoryDto read(String id) {
        Category category=repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not found"));
        return entityToDto(category);
    }
    @Override
    public CategoryDto update(CategoryDto dto, String id) {
        Category category = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not found"));
        category.setName(dto.getName());
        category.setDesc(dto.getDesc());
        category = repo.save(category);
        return entityToDto(category);
    }

    @Override
    public void delete(String id) {
        Category category = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not found"));
        repo.deleteById(id);
    }

    @Override
    public CustomPageResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy)
    {
        Sort sort=Sort.by(sortBy).ascending();
        PageRequest request = PageRequest.of(pageNumber, pageSize,sort);
        Page<Category> categoryPage;
        try {
            categoryPage = repo.findAll(request);
        }
        catch (Exception ex)
        {
            throw new InvalidParametersException();
        }

        List<Category> categoryList=categoryPage.stream().toList();
        List<CategoryDto> categoryDtoList=categoryList.stream().map(this::entityToDto).toList();
        return new CustomPageResponse<>(
                pageNumber,
                pageSize,
                categoryDtoList,
                categoryPage.isLast()
        );


    }

    Category dtoToEntity(CategoryDto dto)
    {
        Category category = mapper.map(dto, Category.class);
        return category;
    }

    CategoryDto entityToDto(Category category)
    {
        CategoryDto dto = mapper.map(category,CategoryDto.class);
        return dto;
    }

//    String createId(String s)
//    {
//        String id = UUID.randomUUID().toString().substring(0,7);
//        while (repo.findById(id).isEmpty())
//        {
//            id=UUID.randomUUID().toString().substring(0,7);
//        }
//        return id;
//    }

}

