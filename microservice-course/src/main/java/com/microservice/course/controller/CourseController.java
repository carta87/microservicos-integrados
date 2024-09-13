package com.microservice.course.controller;

import com.library.entidades.jpa.entity.Course;
import com.microservice.course.service.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/course")
public class CourseController {

    private final ICourseService iCourseService;

    @PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCourse(@RequestBody Course course){
        iCourseService.save(course);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(iCourseService.findAll());
    }

    @GetMapping(path = "/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(iCourseService.findById(id));
    }

    @GetMapping(path = "/search-student/{idCourse}")
    public ResponseEntity<?> findStudensByIdCourse(@PathVariable Long idCourse){
        return ResponseEntity.ok(iCourseService.findStudentsByIdCourse(idCourse));
    }
}
