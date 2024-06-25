package com.i2i.sma.controller;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

public class AdminController {
    private static Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private AdminService adminService = new AdminService();

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
    public void addAdmin() {
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
            logger.debug("RECEIVED INPUTS NAME: {}, PASSWORD: {} ", name, password);
            System.out.println(adminService.addAdminDetails(name, password));
            System.out.println("\nAdmin data has been added successfully");
            logger.info("ADMIN DETAILS OF NAME: {} ADDED SUCCESSFULLY ", name);
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
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
    public boolean isAdmin(String name, String password){
        try{
            return adminService.isCheckAdmin(name, password);
        } catch (SchoolManagementException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return false;
    }
}