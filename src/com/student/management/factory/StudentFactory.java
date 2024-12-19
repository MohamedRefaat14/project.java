package com.student.management.factory;

import com.student.management.factory.Student;
import com.student.management.studentType.GraduateStudent;
import com.student.management.studentType.PartTimeStudent;
import com.student.management.studentType.UndergraduateStudent;

public class StudentFactory {
    public static Student createStudent(String type, String id, String name) {
        switch (type.toLowerCase()) {
            case "undergraduate":
                return new UndergraduateStudent(id, name);
            case "graduate":
                return new GraduateStudent(id, name);
            case "part-time":
                return new PartTimeStudent(id, name);
            default:
                throw new IllegalArgumentException("Invalid student type: " + type);
        }
    }
}
