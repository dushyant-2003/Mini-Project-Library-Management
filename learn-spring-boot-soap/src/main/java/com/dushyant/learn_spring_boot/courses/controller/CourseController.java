package com.dushyant.learn_spring_boot.courses.controller;



import java.util.Arrays;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dushyant.learn_spring_boot.courses.bean.Course;
import com.dushyant.learn_spring_boot.courses.repository.CourseRepository;

@RestController
public class CourseController {

	@Autowired
	private CourseRepository repository;
	
	
	// URL - http://localhost:8080/courses
	@GetMapping("/courses")
	public List<Course> getAllCourses()
	{
		
		return repository.findAll();		
	}
	
	
	@GetMapping("/courses/{id}")
	public Course getCourseDetails(@PathVariable long id)
	{
		Optional<Course> course = repository.findById(id);
		if(course.isEmpty())
		{
			throw new RuntimeException("course not found with id " + id); 
		}
		return course.get();
	}
	
	@PostMapping("/courses")
	public void createCourse(@RequestBody Course course)
	{
		repository.save(course);
	}
	@PutMapping("/courses")
	public void updateCourse(@RequestBody Course course)
	{
		repository.save(course);
	}
	@DeleteMapping("/courses/{id}")
	public void deleteCourse(@PathVariable long id)
	{
		repository.deleteById(id);
	}
}
