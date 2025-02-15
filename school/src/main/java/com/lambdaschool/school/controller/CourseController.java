package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.service.CourseService;
import com.lambdaschool.school.view.CountStudentsInCourses;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/courses")
public class CourseController
{
	@Autowired
	private CourseService courseService;

	@ApiOperation(value = "Returns a list of all the courses",
			response = Course.class,
			responseContainer = "List")
	@GetMapping(value = "/courses",
			produces = {"application/json"})
	public ResponseEntity<?> listAllCourses()
	{
		ArrayList<Course> myCourses = courseService.findAll();
		return new ResponseEntity<>(myCourses, HttpStatus.OK);
	}

	@ApiOperation(value = "Returns a list of all the courses and the number of students in each course",
			response = Course.class,
			responseContainer = "List")
	@GetMapping(value = "/studcount",
			produces = {"application/json"})
	public ResponseEntity<?> getCountStudentsInCourses()
	{
		return new ResponseEntity<>(courseService.getCountStudentsInCourse(), HttpStatus.OK);
	}

	@ApiOperation(value = "Deletes a course")
	@DeleteMapping("/courses/{courseid}")
	@ApiResponses(value = {@ApiResponse(code=200, message = "Course Deleted"),
	@ApiResponse(code= 404, message = "Course Id Not Found")})
	public ResponseEntity<?> deleteCourseById(
			@ApiParam(value = "Id of the course", required = true, example = "1")
			@PathVariable
					long courseid)
	{
		courseService.delete(courseid);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
