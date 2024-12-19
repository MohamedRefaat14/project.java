package com.student.management;

import java.util.*;

public class GradeProcessingSystem {
    private static GradeProcessingSystem instance;

    private final Map<String, Map<String, Double>> studentGrades = new HashMap<>();

    private GradeProcessingSystem() {}

    public static GradeProcessingSystem getInstance() {
        if (instance == null) {
            instance = new GradeProcessingSystem();
        }
        return instance;
    }

    public void addGrade(String studentId, String courseId, double grade) {
        studentGrades.putIfAbsent(studentId, new HashMap<>());
        studentGrades.get(studentId).put(courseId, grade);
        System.out.println("Grade added for student " + studentId + " in course " + courseId);
    }

    public void displayGrades(String studentId) {
        Map<String, Double> grades = studentGrades.get(studentId);
        if (grades == null || grades.isEmpty()) {
            System.out.println("No grades available for student " + studentId);
        } else {
            System.out.println("Grades for student " + studentId + ":");
            grades.forEach((courseId, grade) -> System.out.println("Course: " + courseId + ", Grade: " + grade));
        }
    }

    String getGrade(String studentId, String courseName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
