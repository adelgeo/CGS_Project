package com.adel.repository;

import com.adel.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    public Professor findByCourses(String name);

}
