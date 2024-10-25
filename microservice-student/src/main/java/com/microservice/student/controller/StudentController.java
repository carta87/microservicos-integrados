package com.microservice.student.controller;

import com.library.entidades.dto.StudentDTO;
import com.microservice.student.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/student")
public class StudentController {

    private final IStudentService iStudentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<byte[]> saveStudent(@RequestBody StudentDTO studentDTO){
       return iStudentService.save(studentDTO);
    }

    @PutMapping
    public ResponseEntity<Void> updateStudent(@RequestBody StudentDTO studentDTO){
        if(iStudentService.updateStudent(studentDTO)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAllStudents(){
        return ResponseEntity.ok(iStudentService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable Long id){
        return iStudentService.findById(id) != null ?
                ResponseEntity.ok(iStudentService.findById(id)) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/search-by-course/{idCourse}")
    public ResponseEntity<List<StudentDTO>> findByIDCourse(@PathVariable Long idCourse){
        return ResponseEntity.ok(iStudentService.findByIdCourse(idCourse));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        return iStudentService.delete(id) ?
                ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
