package com.i2i.sma.repository;

import com.i2i.sma.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    Grade findByStandardAndSection(int standard, String section);
}
