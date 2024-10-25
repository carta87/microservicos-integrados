package com.microservice.student.repository;

import com.library.entidades.jpa.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IStudendRepository extends CrudRepository<Student, Long> {

    List<Student> findAllByCourseNumber(Long courseNumber);

    @Query("select s from Student s where s.courseNumber = :courseNumber")
    List<Student> findAllStudent(Long courseNumber);
}
