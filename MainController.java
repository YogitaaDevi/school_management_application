import com.i2i.sma.controller.GradeController;
import com.i2i.sma.controller.StudentController;
import com.i2i.sma.controller.TeacherController;
import com.i2i.sma.exception.SchoolManagementException;
import java.lang.Exception;
import java.util.Scanner;

/**
* <p> 
* This application is built for the purpose of maintaining the school details that includes students, grades and teacher details
* The maincontroller class is responsible for performing the necessary operations and managing the records of students, grades and teachers.
* It provides functionalities to create, add, display, search and delete the details of students, grades and teachers
* by using the corresponding controller class of them.
* </p>
*/
class MainController {
    private static Scanner scanner = new Scanner(System.in);
    private StudentController studentController = new StudentController();  
    private TeacherController teacherController = new TeacherController();
    private GradeController gradeController = new GradeController();

    public static void main(String args[]) {
        MainController mainController = new MainController();
        mainController.startExecution();
    } 

    /**
    * <p> 
    * This method is responsible for the functionalities to create, add, display, search and delete the details of students, grades and teachers.
    * It prompts the user to choose whether to make changes in student, grade or teacher details.
    * After getting the choice from the user, it performs the action by using the corresponding controller.
    * </p>
    */
    public void startExecution() {
        boolean flag = true;
        boolean isExit;
        int option;
        while(flag) {
            System.out.println("\n----------------------------------");
            System.out.println("Enter your choice");
            System.out.println("1: Add Details");
            System.out.println("2: View Details");
            System.out.println("3: Search Details");
            System.out.println("4: Remove Details");
            System.out.println("5: Exit");
            int choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    isExit = true;
                    while(isExit) {
                        System.out.println("----------------------------------");
                        System.out.println("\nWhat details you are going to add?");
                        System.out.println("\n1: Student Details");
                        System.out.println("\n2: Teacher Details\n");
                        option = scanner.nextInt();
                        switch(option) {
                            case 1:
                                studentController.addStudent();
                                isExit = false;
                                break;
	                    case 2:
                                teacherController.addTeacher();
                                isExit = false;
                                break;
                            default:
                                System.out.println("\nEnter a valid choice within 1-2");
                        }
                    }
                    break;
                case 2:
                    isExit = true;
                    while(isExit) {
                        System.out.println("----------------------------------");
                        System.out.println("\nWhat details you are going to view?");
                        System.out.println("\n1: Student Details");
                        System.out.println("\n2: Teacher Details");
                        System.out.println("\n3: Grade Details\n");
                        option = scanner.nextInt();
                        switch(option) {
                            case 1:
                                studentController.viewStudents();
                                isExit = false;
                                break;
	                    case 2:
                                teacherController.viewTeachers();
                                isExit = false;
                                break;
	                    case 3:
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
                    while(isExit) {
                    System.out.println("----------------------------------");
                    System.out.println("\nWhich details you are going to search?");
                    System.out.println("\n1: Student Details");
                    System.out.println("\n2: Teacher Details");
                    System.out.println("\n3: Grade Details\n");
                    option = scanner.nextInt();
                    switch(option) {
                        case 1:
                            studentController.searchStudent();
                             isExit = false;
		             break;
	                 case 2:
                             teacherController.searchTeacher();
                             isExit = false;
                             break;
	                 case 3:
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
                    while(isExit) {
                        System.out.println("----------------------------------");
                        System.out.println("\nWhich details you are going to remove?");
                        System.out.println("\n1: Student Details");
                        System.out.println("\n2: Teacher Details");
                        System.out.println("\n3: Grade Details\n");
                        option = scanner.nextInt();
                        switch(option) {
                            case 1:
                                studentController.removeStudent();
                                isExit = false;
		                break;
	                    case 2:
                                teacherController.removeTeacher();
                                isExit = false;
                                break;
	                    case 3:
                                gradeController.removeGrade();
                                isExit = false;
                                break;
                            default:
                                System.out.println("\nEnter a valid choice within 1-3");
                        }
                    }
                    break;
                case 5:
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