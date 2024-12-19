package com.student.management;

public class CourseFactory {
    public static Course createCourse(int courseId, String courseName, boolean isCore) {
        if (isCore) {
            return new CoreCourse(courseId, courseName);
        } else {
            return new ElectiveCourse(courseId, courseName);
        }
    }

    static Course createCourse(String elective) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
