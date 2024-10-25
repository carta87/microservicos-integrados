package com.microservice.student.repository;

import com.library.entidades.jpa.entity.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IStudendRepository extends CrudRepository<StudentEntity, Long> {

    List<StudentEntity> findAllByCourseNumber(Long courseNumber);

    @Query("select s from StudentEntity s where s.courseNumber = :courseNumber")
    List<StudentEntity> findAllStudent(Long courseNumber);
}
