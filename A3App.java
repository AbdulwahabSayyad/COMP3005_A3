import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Assignment 3 - Q1
 * Abdul Sayyad
 * 101212115
 */

public class A3App {
    //Everything is static as this is mostly a script rather than an OOP app
    public static Connection connection;
    public static final String URL = "jdbc:postgresql://localhost:5432/A3";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        boolean appFlag = true;

        //create the connection
        try {
            String url = URL;
            String username = USER;
            String password = PASSWORD;
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        int userInput = -1;
        System.out.println("Welcome to the A3 PostgreSQL connector. Please select an option from below:");

        while(appFlag){
            
            //Menu
            System.out.println("1. Retrieve all student records");
            System.out.println("2. Insert a student record");
            System.out.println("3. Update the email of a student");
            System.out.println("4. Delete a student record");
            System.out.println("5. Exit");

            userInput = scanner.nextInt();

            if(userInput == 1){
                getAllStudents();
            }
            else if(userInput == 2){
                System.out.println("Enter first name: ");
                String first_name = scanner.next();
                System.out.println("Enter last name: ");
                String last_name = scanner.next();
                System.out.println("Enter email: ");
                String email = scanner.next();
                System.out.println("Enter enrollment date in the format yyyy-mm-dd: ");
                String enrollment_date = scanner.next();
                addStudent(first_name, last_name, email, Date.valueOf(enrollment_date));
            }
            else if (userInput == 3){
                System.out.println("Enter student ID: ");
                String student_id = scanner.next();
                System.out.println("Enter new email: ");
                String new_email = scanner.next();
                updateStudentEmail(Integer.valueOf(student_id), new_email);
            }
            else if (userInput == 4){
                System.out.println("Enter student ID: ");
                String student_id = scanner.next();
                deleteStudent(Integer.valueOf(student_id));
            }
            else if (userInput == 5){
                System.out.println("Thank you for using the app.");
                appFlag = false;
            }
            else {
                System.out.println("Unkown input. Please try again: \n");
            }
            
        }

        //close connection and scanner
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    /**
     * Retreives all the records from the students table
     */
    public static void getAllStudents() {
        try {
            //create prepared statement then execute it. Resultset will contain the records after execution
            PreparedStatement statement = connection.prepareStatement("select * from students");
            ResultSet resultSet = statement.executeQuery();

            //go through the result set and print all the info for the records (hopefully my print statements aren't that ugly)
            while (resultSet.next()) {
                System.out.println(
                    "Student ID: " + resultSet.getInt("student_id") + "\n" +
                    "First Name: " + resultSet.getString("first_name") + "\n" +
                    "Last Name: " + resultSet.getString("last_name") + "\n" +
                    "Email: " + resultSet.getString("email") + "\n" +
                    "Enrollment Date: " + resultSet.getDate("enrollment_date")
                );
                System.out.println("--------------------------");
            }
            System.out.println("\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new student record with the input parameters as values
     * @param first_name
     * @param last_name
     * @param email
     * @param enrollment_date
     */
    public static void addStudent(String first_name, String last_name, String email, Date enrollment_date) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into students(first_name,last_name,email,enrollment_date) values(?,?,?,?)");
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, email);
            statement.setDate(4, enrollment_date);
            statement.execute();
            System.out.println("New student record has been created: " + first_name + " " + last_name + "\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the email of an existing student record with the entered student id
     * @param student_id
     * @param new_email
     */
    public static void updateStudentEmail(int student_id, String new_email) {
        try {
            PreparedStatement statement = connection.prepareStatement("update students set email=? WHERE student_id=?");
            statement.setString(1, new_email);
            statement.setInt(2, student_id);
            statement.execute();
            System.out.println("Student #" + student_id + " email has been updated to: " + new_email + "\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes an existing student record with the entered student id
     * @param student_id
     */
    public static void deleteStudent(int student_id) {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from students where student_id=?");
            statement.setInt(1, student_id);
            statement.execute();
            System.out.println("Record for student #" + student_id + " has been deleted from the DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}