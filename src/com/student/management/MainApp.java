package com.student.management;

import com.student.management.factory.*;
import com.student.management.courseType.*;
import com.student.management.singleton.*;

public class MainApp {
    public static void main(String[] args) {
        // Student Registration using Factory Pattern
        Student student1 = StudentFactory.createStudent("undergraduate", "S101", "Alice");
        Student student2 = StudentFactory.createStudent("graduate", "S102", "Bob");
        Student student3 = StudentFactory.createStudent("part-time", "S103", "Charlie");

        student1.displayDetails();
        student2.displayDetails();
        student3.displayDetails();

        CourseRegistrationSystem courseRegSys = CourseRegistrationSystem.getInstance();

        courseRegSys.registerCourse("S101", new CoreCourse(1, "CSE101"));
        courseRegSys.registerCourse("S101", new CoreCourse(2, "CSE102"));
        courseRegSys.registerCourse("S102", new CoreCourse(1, "CSE101"));

        System.out.println("\n--- Course Registration System ---");
        courseRegSys.displayStudentCourses("S101");
        courseRegSys.displayStudentCourses("S102");

        GradeProcessingSystem gradeSystem = GradeProcessingSystem.getInstance();

        gradeSystem.addGrade("S101", "CSE101", 85.5);
        gradeSystem.addGrade("S101", "CSE102", 90.0);
        gradeSystem.addGrade("S102", "CSE101", 78.0);

        System.out.println("\n--- Grade Processing System ---");
        gradeSystem.displayGrades("S101");
        gradeSystem.displayGrades("S102");

        System.out.println("\n--- Factory Pattern: Course Creation ---");
        Course coreCourse = CourseFactory.createCourse(1, "Math 101", true);
        Course electiveCourse = CourseFactory.createCourse(2, "History 201", false);

        coreCourse.displayCourseDetails();
        electiveCourse.displayCourseDetails();
    }
}
