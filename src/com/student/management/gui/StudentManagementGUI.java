package com.student.management.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Student {
    String id, name, type;
    public Student(String id, String name, String type) { this.id = id; this.name = name; this.type = type; }
}

class StudentCourse {
    String courseId, studentId, courseName, courseType, grade, courseTime;
    public StudentCourse(String courseId, String studentId, String courseName, String courseType, String grade, String courseTime) {
        this.courseId = courseId; this.studentId = studentId; this.courseName = courseName;
        this.courseType = courseType; this.grade = grade; this.courseTime = courseTime;
    }
}

public class StudentManagementGUI {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JTextField studentNameField, studentIdField, courseIdField, courseNameField, gradeField;
    private JComboBox<String> studentTypeCombo, studentIdCombo, courseTypeCombo, courseDayCombo, courseTimeCombo, gradeStudentIdCombo, gradeCourseCombo, studentSelectorCombo;
    private JTextArea registeredStudentsArea, registeredCoursesArea, gradesArea;
    private DefaultTableModel tableModel;

    private List<Student> students = new ArrayList<>();
    private List<StudentCourse> courses = new ArrayList<>();

    public StudentManagementGUI() {
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);

        tabbedPane = new JTabbedPane();
        initStudentRegistrationTab();
        initCourseRegistrationTab();
        initGradeAssignmentTab();
        initStudentCourseDisplayTab();

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private void initStudentRegistrationTab() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        studentNameField = new JTextField(); studentIdField = new JTextField();
        studentTypeCombo = new JComboBox<>(new String[]{"Undergraduate", "Graduate", "Part-Time"});
        JButton registerButton = new JButton("Register Student");
        registerButton.addActionListener(e -> registerStudent());

        inputPanel.add(new JLabel("Student Name:")); inputPanel.add(studentNameField);
        inputPanel.add(new JLabel("Student ID:")); inputPanel.add(studentIdField);
        inputPanel.add(new JLabel("Student Type:")); inputPanel.add(studentTypeCombo);
        inputPanel.add(registerButton); 
        JButton deleteStudentButton = new JButton("Delete Student");
        deleteStudentButton.addActionListener(e -> deleteStudent());
        inputPanel.add(deleteStudentButton);

        registeredStudentsArea = new JTextArea();
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(registeredStudentsArea), BorderLayout.CENTER);
        tabbedPane.addTab("Student Registration", panel);
    }

    private void initCourseRegistrationTab() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));

        courseIdField = new JTextField(); courseNameField = new JTextField();
        courseTypeCombo = new JComboBox<>(new String[]{"Core", "Elective"});
        studentIdCombo = new JComboBox<>(); courseDayCombo = new JComboBox<>(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"});
        courseTimeCombo = new JComboBox<>(new String[]{"9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM"});

        JButton registerCourseButton = new JButton("Register Course");
        registerCourseButton.addActionListener(e -> registerCourse());
        JButton deleteCourseButton = new JButton("Delete Course");
        deleteCourseButton.addActionListener(e -> deleteCourse());

        inputPanel.add(new JLabel("Course ID:")); inputPanel.add(courseIdField);
        inputPanel.add(new JLabel("Course Name:")); inputPanel.add(courseNameField);
        inputPanel.add(new JLabel("Course Type:")); inputPanel.add(courseTypeCombo);
        inputPanel.add(new JLabel("Student ID:")); inputPanel.add(studentIdCombo);
        inputPanel.add(new JLabel("Course Day:")); inputPanel.add(courseDayCombo);
        inputPanel.add(new JLabel("Course Time:")); inputPanel.add(courseTimeCombo);
        inputPanel.add(registerCourseButton); inputPanel.add(deleteCourseButton);

        registeredCoursesArea = new JTextArea();
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(registeredCoursesArea), BorderLayout.CENTER);
        tabbedPane.addTab("Course Registration", panel);
    }

    private void initGradeAssignmentTab() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        gradeStudentIdCombo = new JComboBox<>(); gradeCourseCombo = new JComboBox<>();
        gradeField = new JTextField();
        JButton assignGradeButton = new JButton("Assign Grade");
        assignGradeButton.addActionListener(e -> assignGrade());

        inputPanel.add(new JLabel("Student ID:")); inputPanel.add(gradeStudentIdCombo);
        inputPanel.add(new JLabel("Course Name:")); inputPanel.add(gradeCourseCombo);
        inputPanel.add(new JLabel("Grade:")); inputPanel.add(gradeField);
        inputPanel.add(assignGradeButton);

        gradesArea = new JTextArea();
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(gradesArea), BorderLayout.CENTER);
        tabbedPane.addTab("Assign Grades", panel);
    }

    private void initStudentCourseDisplayTab() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());

        studentSelectorCombo = new JComboBox<>();
        studentSelectorCombo.addActionListener(e -> refreshStudentCoursesTable());
        topPanel.add(new JLabel("Select Student: "), BorderLayout.WEST);
        topPanel.add(studentSelectorCombo, BorderLayout.CENTER);
        panel.add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"Course ID", "Course Name", "Course Type", "Course Time", "Grade"}, 0);
        JTable studentCourseTable = new JTable(tableModel);
        panel.add(new JScrollPane(studentCourseTable), BorderLayout.CENTER);
        tabbedPane.addTab("Student Courses", panel);
    }

    private void registerStudent() {
        String name = studentNameField.getText(), id = studentIdField.getText();
        if (name.isEmpty() || id.isEmpty() || students.stream().anyMatch(s -> s.id.equals(id))) {
            JOptionPane.showMessageDialog(frame, "Please enter all fields and ensure the ID is unique", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        students.add(new Student(id, name, (String) studentTypeCombo.getSelectedItem()));
        studentIdCombo.addItem(id); gradeStudentIdCombo.addItem(id); studentSelectorCombo.addItem(id);
        registeredStudentsArea.append(id + " - " + name + "\n");
        studentNameField.setText(""); studentIdField.setText(""); studentTypeCombo.setSelectedIndex(0);
        JOptionPane.showMessageDialog(frame, "Student registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteStudent() {
        String idToDelete = studentIdField.getText();
        if (idToDelete.isEmpty()) { JOptionPane.showMessageDialog(frame, "Please enter the student ID to delete", "Error", JOptionPane.ERROR_MESSAGE); return; }

        students.removeIf(student -> student.id.equals(idToDelete));
        studentIdCombo.removeItem(idToDelete); gradeStudentIdCombo.removeItem(idToDelete); studentSelectorCombo.removeItem(idToDelete);
        updateRegisteredStudentsArea();
        studentIdField.setText(""); JOptionPane.showMessageDialog(frame, "Student deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

   private void registerCourse() {
    String courseId = courseIdField.getText(), courseName = courseNameField.getText(),
           studentId = (String) studentIdCombo.getSelectedItem(), courseDay = (String) courseDayCombo.getSelectedItem(),
           courseTime = (String) courseTimeCombo.getSelectedItem();

    if (courseId.isEmpty() || courseName.isEmpty() || studentId == null || courseDay == null || courseTime == null) {
        JOptionPane.showMessageDialog(frame, "Please enter all fields", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (courses.stream().anyMatch(course -> course.courseId.equals(courseId) && course.studentId.equals(studentId))) {
        JOptionPane.showMessageDialog(frame, "Student already registered for this course", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Register the course
    courses.add(new StudentCourse(courseId, studentId, courseName, (String) courseTypeCombo.getSelectedItem(), "N/A", courseDay + " " + courseTime));
    
    // Update the course name combo box for grade assignment
    gradeCourseCombo.addItem(courseName); // Add course name to grade combo

    // Append to registered courses area
    registeredCoursesArea.append(courseId + " - " + studentId + " - " + courseName + "\n");

    // Reset the course registration form
    courseIdField.setText(""); courseNameField.setText(""); courseTypeCombo.setSelectedIndex(0); 
    courseDayCombo.setSelectedIndex(0); courseTimeCombo.setSelectedIndex(0);

    JOptionPane.showMessageDialog(frame, "Course registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
}

    private void deleteCourse() {
        String courseIdToDelete = courseIdField.getText();
        if (courseIdToDelete.isEmpty()) { JOptionPane.showMessageDialog(frame, "Please enter the course ID to delete", "Error", JOptionPane.ERROR_MESSAGE); return; }

        courses.removeIf(course -> course.courseId.equals(courseIdToDelete));
        updateRegisteredCoursesArea();
        courseIdField.setText(""); JOptionPane.showMessageDialog(frame, "Course deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void assignGrade() {
        String studentId = (String) gradeStudentIdCombo.getSelectedItem(), courseName = (String) gradeCourseCombo.getSelectedItem(),
               grade = gradeField.getText();

        if (studentId == null || courseName == null || grade.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        courses.stream().filter(course -> course.studentId.equals(studentId) && course.courseName.equals(courseName))
            .forEach(course -> course.grade = grade);

        gradesArea.append(studentId + " - " + courseName + " : " + grade + "\n");
        gradeField.setText(""); JOptionPane.showMessageDialog(frame, "Grade assigned successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void refreshStudentCoursesTable() {
        String studentId = (String) studentSelectorCombo.getSelectedItem();
        tableModel.setRowCount(0);
        courses.stream().filter(course -> course.studentId.equals(studentId))
            .forEach(course -> tableModel.addRow(new Object[]{course.courseId, course.courseName, course.courseType, course.courseTime, course.grade}));
    }

    private void updateRegisteredStudentsArea() {
        registeredStudentsArea.setText(""); students.forEach(student -> registeredStudentsArea.append(student.id + " - " + student.name + "\n"));
    }

    private void updateRegisteredCoursesArea() {
        registeredCoursesArea.setText(""); courses.forEach(course -> registeredCoursesArea.append(course.courseId + " - " + course.studentId + " - " + course.courseName + "\n"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementGUI::new);
    }
}
