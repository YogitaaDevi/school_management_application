package com.i2i.sma.service;

import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.models.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminServiceInterface {
        Admin addAdminDetails(String name, String password) throws SchoolManagementException;
        boolean isCheckAdmin(String name, String password) throws SchoolManagementException;
        List<Admin> fetchAdmins() throws SchoolManagementException;
        Optional<Admin> findAdmin(int id) throws SchoolManagementException;
        boolean isDeleteAdmin(int id) throws SchoolManagementException;
}
