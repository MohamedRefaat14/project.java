package com.student.management;

public class CoreCourse extends Course {
    public CoreCourse(int courseId, String courseName) {
        super(courseId, courseName);
    }

    @Override
    public boolean isCore() {
        return true;
    }

    @Override
    void displayCourseDetails() {
        System.out.println("Course ID: " + getCourseId() + ", Course Name: " + getCourseName() + ", Type: Core Course");
    }
}
