package com.i2i.sma.controller;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.sma.exception.SchoolManagementException;
import com.i2i.sma.models.Admin;
import com.i2i.sma.service.AdminService;
import com.i2i.sma.utils.DataValidationUtil;

/**
 * <p>
 * This class is responsible for managing admin records.
 * This class can be handled only by the main admin.
 * It provides functionalities to create and add new admins and search an admin.
 * </p>
 */
@RestController
@RequestMapping("sma/api/v1.0/admin")
public class AdminController {
    private static Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;

    /**
     * <p>
     * This method handles the add a new admin record with their unique password.
     * This
     * It prompts the main admin to enter the admin's name, and password.
     * After collecting the information from the user, it validates
     * whether the input from user is in correct format.
     * For example : checks whether entered name contains only alphabets,
     * After validations, it adds the admin to the database
     * Once the admin is successfully added, it prints out the admin's details and a success message.
     * </p>
     */

    @PostMapping("/addAdmin")
    public Admin addAdmin() {
        String name;
        String password;
        while (true) {
            System.out.println("\nEnter an admin name : ");
            name = scanner.next();
            if (!DataValidationUtil.validateString(name)) {
                System.out.println("PLEASE ENTER A PROPER NAME. MUST BE ONLY ALPHABETS");
                continue;
            }
            break;
        }
        System.out.println("Enter a password : ");
        password = scanner.next();
        try {
            Admin adminDetails = adminService.addAdminDetails(name, password);
            System.out.println(adminDetails);
            System.out.println("\nAdmin data has been added successfully");
            logger.info("ADMIN DETAILS OF NAME: {} ADDED SUCCESSFULLY ", name);
            return adminDetails;
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * <p>
     * This method handles displaying all admins record.
     * It calls fetchAdmins method and displays the all admin details that are available.
     * </p>
     */
    @GetMapping("/viewAdmins")
    public List<Admin> viewAdmins() {
        try {
            List<Admin> Details = adminService.fetchAdmins();
            if (null != Details) {
                for (Admin admin : Details) {
                    System.out.println(admin);
                }
                logger.info("ALL ADMINS DATA ARE DISPLAYED SUCCESSFULLY");
            } else {
                System.out.println("NO STUDENT DATA FOUND IN THE DATABASE");
                logger.warn("NO ADMINS FOUND IN DATABASE");
            }
            return Details;
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * <p>
     * This method checks whether the given admin name and password matches is found.
     * @param name
     *   admin's name in string. Only alphabets are allowed
     * @param password
     *   admin's password in string. Can be alphabets or alphanumeric or numbers.
     * @return
     *   true if the specified admin details found or else false.
     * </p>
     */
    @GetMapping("/checkAdmin")
    public boolean isAdmin(String name, String password){
        try{
            return adminService.isCheckAdmin(name, password);
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * <p>
     * This method handles searching admins from record.
     * It prompts the user to enter the admin id they wish to see the details.
     * After getting id from the user, it retrieves data and display it to the user.
     * If the user given wrong id, it displays a warning message.
     * For example: provide valid admin id.
     * </p>
     */
    @GetMapping("/deleteAdmin/{id}")
    public Optional<Admin> searchAdmin(@PathVariable int id) {
        System.out.println("Enter the ID to search: ");
        try {
            Optional<Admin> searchedAdmin = adminService.findAdmin(id);
            if (searchedAdmin.isPresent()) {
                System.out.println(searchedAdmin);
                logger.info("ADMIN ID: {} FOUND SUCCESSFULLY", id);
            } else {
                System.out.println("THERE IS NO SUCH ADMIN " + id + " EXIST ");
                logger.warn("CANNOT FIND ADMIN OF ID: {}", id);
            }
            return searchedAdmin;
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage(), e);

        }
        return null;
    }

    /**
     * <p>
     * This method handles removing admins from record.
     * It prompts the user to enter the id of the admin they wish to remove the details.
     * After getting id from the user, it deletes the particular data.
     * After removing, it displays a successful message.
     * If the user given wrong id, it displays a warning message.
     * For example: provide valid admin id.
     * </p>
     */
    @DeleteMapping("/deleteAdmin")
    public void removeAdmin() {
        System.out.println("Enter the ID to delete: ");
        int id = scanner.nextInt();
        try {
            System.out.println((adminService.isDeleteAdmin(id)) ? "\nADMIN ID "
                    + id + " REMOVED SUCCESSFULLY" : "\nERROR WHILE DELETING ADMIN ID "
                    + id + "\nPLEASE CHECK THE ADMIN ID PROPERLY");
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage(), e);

        }
    }
}