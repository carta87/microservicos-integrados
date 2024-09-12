package com.microservice.student.controller;

import com.microservice.student.entities.Student;
import com.microservice.student.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/student")
public class StudentController {

    private final IStudentService iStudentService;

    @PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudent(@RequestBody Student student){
        iStudentService.save(student);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<?> findAllStudents(){
        return ResponseEntity.ok(iStudentService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(iStudentService.findById(id));
    }

    @GetMapping(path = "/serarch-by-course/{idCourse}")
    public ResponseEntity<?> findByIDCourse(@PathVariable  Long idCourse){
        return ResponseEntity.ok(iStudentService.findByIdCourse(idCourse));
    }
}
