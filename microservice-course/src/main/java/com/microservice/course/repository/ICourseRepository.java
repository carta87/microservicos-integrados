package com.microservice.course.repository;

import com.library.entidades.jpa.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ICourseRepository extends CrudRepository<Course, Long> {
    Optional<Course> findByNumberCourse(Long numberCourse);

    boolean existsByNumberCourse(Long numberCourse);
}
