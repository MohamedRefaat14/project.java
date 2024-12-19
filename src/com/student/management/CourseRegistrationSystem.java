package com.student.management;

import java.util.*;

public class CourseRegistrationSystem {
    private static CourseRegistrationSystem instance;
    private Map<String, Map<Integer, String>> studentGrades;
    private List<Course> availableCourses;

    private CourseRegistrationSystem() {
        studentGrades = new HashMap<>();
        availableCourses = new ArrayList<>();

        availableCourses.add(new CoreCourse(1, "Math 101"));
        availableCourses.add(new ElectiveCourse(2, "History 201"));
        availableCourses.add(new CoreCourse(3, "Computer Science 101"));
        availableCourses.add(new ElectiveCourse(4, "Art 101"));
    }

    public static CourseRegistrationSystem getInstance() {
        if (instance == null) {
            instance = new CourseRegistrationSystem();
        }
        return instance;
    }

    public void registerCourse(String studentId, Course course) {
        studentGrades.putIfAbsent(studentId, new HashMap<>());
        studentGrades.get(studentId).put(course.getCourseId(), null);
    }

    public void addGrade(String studentId, int courseId, String grade) {
        try {
            double numericGrade = Double.parseDouble(grade);
            Map<Integer, String> grades = studentGrades.get(studentId);
            if (grades != null) {
                grades.put(courseId, grade);
            } else {
                System.out.println("Student not found or not registered in this course.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid grade format. Please enter a valid number.");
        }
    }

    public String getGrade(String studentId, int courseId) {
        Map<Integer, String> grades = studentGrades.get(studentId);
        return (grades != null) ? grades.get(courseId) : null;
    }

    public List<Course> getAvailableCourses() {
        return availableCourses;
    }

    public List<Course> getStudentCourses(String studentId) {
        List<Course> courses = new ArrayList<>();
        Map<Integer, String> grades = studentGrades.get(studentId);
        if (grades != null) {
            for (Map.Entry<Integer, String> entry : grades.entrySet()) {
                Course course = getCourseById(entry.getKey());
                if (course != null) {
                    courses.add(course);
                }
            }
        }
        return courses;
    }

    private Course getCourseById(int courseId) {
        for (Course course : availableCourses) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        return null;
    }

    public void displayStudentCourses(String studentId) {
        List<Course> courses = getStudentCourses(studentId);
        if (courses.isEmpty()) {
            System.out.println("Student " + studentId + " is not registered for any courses.");
        } else {
            System.out.println("Courses registered by student " + studentId + ":");
            for (Course course : courses) {
                System.out.println(course.getCourseName());
            }
        }
    }
}
