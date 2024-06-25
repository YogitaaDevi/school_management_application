package com.i2i.sma.service;

import com.i2i.sma.dao.AdminDao;
import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.models.Admin;

/**
 * <p>
 * This class is responsible for managing admin details.
 * It provides functionalities to add and fetch admin details.
 * </p>
 */

public class AdminService {
    private AdminDao adminDao = new AdminDao();

    /**
     * <p>
     * This method deals with adding the admin details.
     * It takes the name and password of the admin and saves the details.
     *
     * @param name
     *   admin's name in string. Only alphabets are allowed
     * @param password
     *   admin's password in string. Can be alphabets or alphanumeric or numbers.
     * @return admin
     *   contains admin id, name.
     * @throws SchoolManagementException this occurs when anything went wrong while adding admin details.
     */
    public Admin addAdminDetails(String name, String password) throws SchoolManagementException {
        Admin admin = new Admin();
        admin.setName(name);
        admin.setPassword(password);
        adminDao.insertAdminDetails(admin);
        return admin;
    }

    /**
     * <p>
     * This method checks if the given admin name and password is found or not.
     * It takes the name and password of the admin and checks the details.
     *
     * @param name
     *   admin's name in string. Only alphabets are allowed
     * @param password
     *   admin's password in string. Can be alphabets or alphanumeric or numbers.
     * @return
     *   true if the given credentails matches or else false.
     * @throws SchoolManagementException this occurs when anything went wrong while fetching admin details.
     */
    public boolean isCheckAdmin(String name, String password) throws SchoolManagementException{
        Admin adminAvailable = adminDao.findAdmin(name, password);
        if(null == adminAvailable){
            return false;
        }
        return true;
    }
}