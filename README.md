# School management application

## Description :

- This application serves as a prototype for the school management system.
- It provides following functionalities:
   1. Create and Add new students, new grades and new teachers for the school.
   2. Display all students, grades and teachers separavailable in the school.
   3. Search for a specific student, grade and teacher in the school.
   4. Deletes a specific student, grade and teacher from the school.

- It prompts the user to choose a specific function of what they want to do. 
Based on the user's choice, the application works accordingly.

## To run this application :
- we need maven a powerful project management tool that is based on POM (project object model).

## Steps to follow :
- To setup and run maven in our system do the following steps:
  1. Install maven 
  2. Setup the classpath in environmental variable
  3. In the command prompt, type 
     - mvn --version (to check the version of the installed maven)
     - mvn clean install(to build our project)
     - mvn compile (to compile our project)
     - mvn exec:java -Dexec.mainClass="MainController" (to run our project) 
