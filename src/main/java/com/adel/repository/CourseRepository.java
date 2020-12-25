package com.adel.repository;

import com.adel.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<Course, Integer> {

    public Course findByCourseId(int id);

    public Course findByName(String name);
}
