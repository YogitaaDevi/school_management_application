package com.i2i.sma.service;

import com.i2i.sma.dao.AdminDao;
import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.models.Admin;

import java.util.List;

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

    /**
     * <p>
     * This method retrieves all the admins record from the database.
     * </p>
     *
     * @return all Admin details in form of list to display it to the enduser.
     * @throws SchoolManagementException
     *   this occurs when anything went wrong while retrieving data.
     */
    public List<Admin> fetchAdmins() throws SchoolManagementException {
        return adminDao.getDetails();
    }

    /**
     * <p>
     * This method retrieves a particular admin record
     * from the database based on the id (unique identifier represents each admin).
     * </p>
     *
     * @param id
     *   the unique identifier of the admin to be retrieved.
     * @return 
     *   an admin corresponding to the provided ID if found. Else null if no such admin is found.
     * @throws SchoolManagementException
     *   this occurs when anything went wrong while searching a data.
     */
    public Admin findAdmin(int id) throws SchoolManagementException {
        return adminDao.findAdminById(id);
    }

    /**
     * <p>
     * This method deletes a particular student record
     * from the database based on the provided student ID.
     * If the given id matches, it removes the student with the specified ID.
     * </p>
     *
     * @param id
     *   the unique identifier of the student to be deleted.
     * @return
     *   true if the specified student id is deleted successfully or else returns false
     * @throws SchoolManagementException
     *   this occurs when anything went wrong while removing a data.
     */
    public boolean isDeleteAdmin(int id) throws SchoolManagementException {
        return adminDao.isRemoveAdmin(id);
    }
}