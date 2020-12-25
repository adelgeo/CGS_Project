package com.adel.repository;

import com.adel.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    //public String findById(String st);

}
