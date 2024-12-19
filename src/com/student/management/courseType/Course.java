package com.student.management.courseType;

public abstract class Course {
    private int courseId;
    String courseName;

    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public abstract boolean isCore();

     public abstract void displayCourseDetails();

    class studentId {

        public studentId() {
        }
    }
}
