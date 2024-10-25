package com.microservice.course.controller;

import com.library.entidades.dto.CourseDTO;
import com.library.entidades.http.response.StudentByCourseResponse;
import com.microservice.course.service.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/course")
public class CourseController {

    private final ICourseService iCourseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCourse(@RequestBody CourseDTO courseDTO){
        iCourseService.save(courseDTO);
    }

    @PutMapping
    public  void  updateCourse(@RequestBody CourseDTO courseDTO){
        iCourseService.update(courseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> findAll(){
        return iCourseService.findAll().isEmpty()?
                new  ResponseEntity<>(HttpStatus.NO_CONTENT):
                new ResponseEntity<>(iCourseService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(iCourseService.findById(id));
    }

    @GetMapping(path = "/search-student/{numberCourse}")
    public ResponseEntity<StudentByCourseResponse> findByStudentsAndIdCourse(@PathVariable Long numberCourse){
        return ResponseEntity.ok(iCourseService.findStudentsByNumberCourse(numberCourse));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        iCourseService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
