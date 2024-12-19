package com.student.management.courseType;

public class ElectiveCourse extends Course {
    public ElectiveCourse(int courseId, String courseName) {
        super(courseId, courseName);
    }

    @Override
    public boolean isCore() {
        return false;
    }

    @Override
    public void displayCourseDetails() {
        System.out.println("Course ID: " + getCourseId() + ", Course Name: " + getCourseName() + ", Type: Elective Course");
    }
}
