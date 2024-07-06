package com.i2i.sma.repository;

import com.i2i.sma.models.Teacher;
import com.i2i.sma.service.TeacherService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
}
