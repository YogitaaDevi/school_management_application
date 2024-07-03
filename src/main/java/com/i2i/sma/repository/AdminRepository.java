package com.i2i.sma.repository;

import com.i2i.sma.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Admin findByNameAndPassword(String name, String password);
}
