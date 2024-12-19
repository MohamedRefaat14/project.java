package com.student.management.factory;

import com.student.management.courseType.CoreCourse;
import com.student.management.courseType.Course;
import com.student.management.courseType.ElectiveCourse;

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
