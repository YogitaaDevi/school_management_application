import java.util.Scanner;

import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.i2i.sma.controller.AdminController;
import com.i2i.sma.controller.GradeController;
import com.i2i.sma.controller.StudentController;
import com.i2i.sma.controller.TeacherController;

/**
 * <p>
 * This application is built for the purpose of maintaining the school details
 * that includes students, grades, teacher and admin details.
 * The maincontroller class is responsible for performing the necessary operations
 * and managing the records of students, grades, teachers and admins.
 * It provides functionalities to create, add, display, search
 * and delete the details of students, grades, teachers and admin
 * by using the corresponding controller class of them.
 * </p>
 */
public class MainController {
    private static Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private AdminController adminController = new AdminController();
    private GradeController gradeController = new GradeController();
    private StudentController studentController = new StudentController();
    private TeacherController teacherController = new TeacherController();

    public static void main(String[] args) {
        MainController mainController = new MainController();
        logger.info("EXECUTION OF APPLICATION STARTS HERE");
        mainController.startExecution();
    }

    /**
     * <p>
     * This method checks the credentials of the main admin to access the database.
     * Main admin is different from other admins.
     * Only Main admin have rights to add and remove other admins
     * It prompts the user to enter the username and password.
     * It checks whether the entered username and password is right or not.
     * </p>
     * @return
     *     true if the entered username and password is correct or else false.
     */
    public boolean isMainAdminAccess() {
        Dotenv dotenv = Dotenv.load();
        System.out.println("Enter main admin username:");
        String uname = scanner.next();
        System.out.println("Enter main admin password:");
        String pwd = scanner.next();
        return uname.equals(dotenv.get("ADMIN_UNAME")) && pwd.equals(dotenv.get("ADMIN_PWD"));
    }

    /**
     * <p>
     * This method is responsible to check the credentials of the user to access the database.
     * It prompts the user to enter the username and password.
     * It checks whether the entered username and password is right or not.
     * </p>
     * @return
     *     true if the entered username and password is correct or else false.
     */
    public boolean isAdminAccess() {
        System.out.println("Enter an admin username:");
        String uname = scanner.next();
        System.out.println("Enter an admin password:");
        String pwd = scanner.next();
        Dotenv dotenv = Dotenv.load();
        boolean isMainAdmin = uname.equals(dotenv.get("ADMIN_UNAME"))
                              && pwd.equals(dotenv.get("ADMIN_PWD"));
        if(!isMainAdmin) {
            return adminController.isAdmin(uname, pwd);
        }
        return true;
    }

    /**
     * <p>
     * This method is responsible for the functionalities to create, add, display,
     * search and delete the details of students, grades and teachers.
     * It prompts the user to choose whether to make changes in student, grade or teacher details.
     * After getting the choice, it performs the action by using the corresponding controller class.
     * </p>
     */
    public void startExecution() {
        boolean flag = true;
        boolean isExit;
        int option;
        while (flag) {
            System.out.println("\n----------------------------------\n");
            System.out.println("Enter your choice");
            System.out.println("1: Add Details (Admin access)");
            System.out.println("2: View Details");
            System.out.println("3: Search Details");
            System.out.println("4: Remove Details (Admin access)");
            System.out.println("5: Admin Details (Only Main Admin access)");
            System.out.println("6: Exit");
            System.out.println("\n----------------------------------\n");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    isExit = true;
                    while (isExit) {
                        if(isAdminAccess()){
                            System.out.println("\n----------------------------------");
                            System.out.println("\nWhat details you are going to add?");
                            System.out.println("\n1: Student Details");
                            System.out.println("\n2: Teacher Details\n");
                            option = scanner.nextInt();
                            switch (option) {
                                case 1:
                                    logger.info("Navigating to the StudentController to add a student");
                                    studentController.addStudent();
                                    isExit = false;
                                    break;
                                case 2:
                                    logger.info("Navigating to the TeacherController to add a teacher");
                                    teacherController.addTeacher();
                                    isExit = false;
                                    break;
                                default:
                                    System.out.println("\nEnter a valid choice within 1-2");
                            }
                        }  else {
                            System.out.println("\nINVALID CREDENTIALS\n");
                            isExit = false;
                        }
                    }
                    break;
                case 2:
                    isExit = true;
                    while (isExit) {
                        System.out.println("\n----------------------------------");
                        System.out.println("\nWhat details you are going to view?");
                        System.out.println("\n1: Student Details");
                        System.out.println("\n2: Teacher Details");
                        System.out.println("\n3: Grade Details\n");
                        option = scanner.nextInt();
                        switch (option) {
                            case 1:
                                logger.info("Navigating to the StudentController to view all students");
                                studentController.viewStudents();
                                isExit = false;
                                break;
                            case 2:
                                logger.info("Navigating to the TeacherController to view all teachers");
                                teacherController.viewTeachers();
                                isExit = false;
                                break;
                            case 3:
                                logger.info("Navigating to the GradeController to view all grades");
                                gradeController.viewGrades();
                                isExit = false;
                                break;
                            default:
                                System.out.println("\nEnter a valid choice within 1-3");
                        }
                    }
                    break;
                case 3:
                    isExit = true;
                    while (isExit) {
                        System.out.println("\n----------------------------------");
                        System.out.println("\nWhich details you are going to search?");
                        System.out.println("\n1: Student Details");
                        System.out.println("\n2: Teacher Details");
                        System.out.println("\n3: Grade Details\n");
                        option = scanner.nextInt();
                        switch (option) {
                            case 1:
                                logger.info("Navigating to the StudentController to search a student");
                                studentController.searchStudent();
                                isExit = false;
                                break;
                            case 2:
                                logger.info("Navigating to the TeacherController to search a teacher");
                                teacherController.searchTeacher();
                                isExit = false;
                                break;
                            case 3:
                                logger.info("Navigating to the GradeController to search a grade");
                                gradeController.searchGrade();
                                isExit = false;
                                break;
                            default:
                                System.out.println("\nEnter a valid choice within 1-3");
                        }
                    }
                    break;
                case 4:
                    isExit = true;
                    while (isExit) {
                        if(isAdminAccess()) {
                            System.out.println("\n----------------------------------");
                            System.out.println("\nWhich details you are going to remove?");
                            System.out.println("\n1: Student Details");
                            System.out.println("\n2: Teacher Details");
                            System.out.println("\n3: Grade Details\n");
                            option = scanner.nextInt();
                            switch (option) {
                                case 1:
                                    logger.info("Navigating to the StudentController to remove a student");
                                    studentController.removeStudent();
                                    isExit = false;
                                    break;
                                case 2:
                                    logger.info("Navigating to the TeacherController to remove a teacher");
                                    teacherController.removeTeacher();
                                    isExit = false;
                                    break;
                                case 3:
                                    logger.info("Navigating to the GradeController to remove a teacher");
                                    gradeController.removeGrade();
                                    isExit = false;
                                    break;
                                default:
                                    System.out.println("\nEnter a valid choice within 1-3");
                            }
                        }  else {
                            System.out.println("INVALID CREDENTIALS\n");
                            isExit = false;
                        }
                    }
                    break;
                case 5:
                    isExit = true;
                    while (isExit) {
                        if(isMainAdminAccess()) {
                            System.out.println("\n----------------------------------");
                            System.out.println("\nChoose the operation are you going to do?");
                            System.out.println("\n1: Add Admin");
                            System.out.println("\n2: Remove Admin\n");
                            option = scanner.nextInt();
                            switch (option) {
                                case 1:
                                    logger.info("Navigating to the AdminController to add a admin");
                                    adminController.addAdmin();
                                    isExit = false;
                                    break;
                                default:
                                    System.out.println("\nEnter a valid choice within 1-2");
                            }
                        }  else {
                            System.out.println("INVALID CREDENTIALS\n");
                            isExit = false;
                        }
                    }
                    break;
                case 6:
                    System.out.println("\nExiting Application...");
                    flag = false;
                    break;
                default:
                    System.out.println("\nEnter a valid choice within 1-5");
            }
            System.out.println("\n----------------------------------");
        }
    }
}