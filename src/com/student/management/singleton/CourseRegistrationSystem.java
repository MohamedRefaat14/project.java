package com.student.management.singleton;


import com.student.management.courseType.CoreCourse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

// MainApp.java (unchanged for now) -- entry point

// CourseRegistrationSystem.java
public class CourseRegistrationSystem {
    private static CourseRegistrationSystem instance;
    private final Map<Integer, Set<String>> studentCourses; // Map: Student ID -> Set of Course IDs

    private CourseRegistrationSystem() {
        studentCourses = new HashMap<>();
    }

    public static CourseRegistrationSystem getInstance() {
        if (instance == null) {
            instance = new CourseRegistrationSystem();
        }
        return instance;
    }

    public boolean registerCourse(int studentId, String courseId) {
        studentCourses.putIfAbsent(studentId, new HashSet<>());
        Set<String> courses = studentCourses.get(studentId);

        if (courses.contains(courseId)) {
            System.out.println("Student already registered for this course.");
            return false;
        }

        courses.add(courseId);
        System.out.println("Course registered successfully for student ID: " + studentId);
        return true;
    }

    public void displayRegistrations() {
        for (Map.Entry<Integer, Set<String>> entry : studentCourses.entrySet()) {
            System.out.println("Student ID: " + entry.getKey() + " -> Courses: " + entry.getValue());
        }
    }

    public void registerCourse(String s101, CoreCourse coreCourse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void displayStudentCourses(String s101) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

// StudentManagementGUI.java
class StudentManagementGUI {
    private final CourseRegistrationSystem registrationSystem;

    public StudentManagementGUI() {
        registrationSystem = CourseRegistrationSystem.getInstance();
    }

    public void showStudentRegistrationPage() {
        System.out.println("=== Student Registration Page ===");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();

        boolean success = registrationSystem.registerCourse(studentId, courseId);
        if (success) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }

    public void showAllRegistrations() {
        System.out.println("=== All Registrations ===");
        registrationSystem.displayRegistrations();
    }
}
