package com.i2i.sma.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.models.Admin;
import com.i2i.sma.repository.AdminRepository;

/**
 * <p>
 * This class is responsible for managing admin details.
 * It provides functionalities to add and fetch admin details.
 * </p>
 */
@Service
public class AdminService implements AdminServiceInterface {
    @Autowired
    private AdminRepository adminRepository;

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
        try{
            return adminRepository.save(admin);
        } catch (Exception e){
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE INSERTING " +
                    "ADMIN DETAILS OF NAME " + admin.getName());
        }
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
        try{
            Admin adminAvailable = adminRepository.findByNameAndPassword(name, password);
            if(null != adminAvailable){
                return true;
            }
            return false;
        } catch (Exception e){
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE CHECKING ADMIN DETAILS OF NAME " + name);
        }
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
        try{
            return adminRepository.findAll();
        } catch (Exception e){
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE FETCHING ALL ADMIN DETAILS");
        }
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
    public Optional<Admin> findAdmin(int id) throws SchoolManagementException {
        try{
            return adminRepository.findById(id);
        } catch (Exception e){
            throw new SchoolManagementException("\nSOMETHING WENT WRONG WHILE SEARCHING THE ADMIN DETAILS OF ID " + id);
        }
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
        Optional<Admin> admin = adminRepository.findById(id);
        if(admin.isPresent()) {
            adminRepository.deleteById(id);
            return true;
        }
        return false;
    }
}