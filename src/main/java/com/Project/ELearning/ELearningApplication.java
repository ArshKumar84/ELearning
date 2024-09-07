package com.Project.ELearning;

import com.Project.ELearning.Entities.Category;
import com.Project.ELearning.Entities.Course;
import com.Project.ELearning.Repositores.CategoryRepo;
import com.Project.ELearning.Repositores.CourseRepo;
import com.Project.ELearning.Services.CourseServiceImpl;
import com.Project.ELearning.Services.CategoryServiceImpl;
import com.Project.ELearning.dtos.CategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class ELearningApplication implements CommandLineRunner {

	@Autowired
	CourseServiceImpl courseService;
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	CourseRepo courseRepo;
    @Autowired
    private CategoryServiceImpl categoryService;
	@Autowired
	private ModelMapper mapper;


	public static void main(String[] args) {
		SpringApplication.run(ELearningApplication.class, args);
	}

	Category dtoToEntity(CategoryDto dto)
	{
		Category category = mapper.map(dto, Category.class);
		return category;
	}

	CategoryDto entityToDto(Category category)
	{
		CategoryDto categoryDto = mapper.map(category,CategoryDto.class);
		return categoryDto;
	}

	@Override
	public void run(String... args) throws Exception {

		MultipartFile file=new


//		CategoryDto categoryDto = new CategoryDto("Mern Stack");
//		Category category=dtoToEntity(categoryDto);
//		Category category1=categoryRepo.save(category);
////		CategoryDto categoryDto1=entityToDto(category1);
//		CategoryDto category=read("1");
//		System.out.println(categoryDto1);


//		Course course4 = new Course("6","Core C","Learn Core C",List.of(categoryRepo.findById("3").get()));
//		Course course5 = new Course("7","C++","Learn C++",List.of(categoryRepo.findById("3").get()));
//
//		ResponseEntity<HttpStatus> response= ResponseEntity.status(HttpStatus.ACCEPTED).body(HttpStatus.ACCEPTED);

//		categoryRepo.findCategoryByName("iPhone").orElseThrow(ResourceNotFoundException::new);

//		courseRepo.saveAll(List.of(course4,course5));

//		Category category1=categoryRepo.findById("1").get();
//		List<Course> courseList1=courseService.getCourseList(category1);
//		System.out.println(courseList1);
//
//		List<Course> courseList=courseRepo.findAllById(List.of("1","2","3"));
////		System.out.println(courseList);
//		categoryService.getCategoryByCourseList(courseList);
	}
}
