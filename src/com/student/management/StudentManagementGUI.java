package com.student.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentManagementGUI extends JFrame {
    private JTextField studentIdField;
    private JTextArea courseTextArea;
    private JTextArea gradeTextArea;
    private final CourseRegistrationSystem courseRegSys;

    public StudentManagementGUI() {
        courseRegSys = CourseRegistrationSystem.getInstance();

        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Add tabs
        tabbedPane.addTab("Register Courses", createCoursePanel());
        tabbedPane.addTab("Enter Grades", createGradePanel());
        tabbedPane.addTab("View Courses and Grades", createViewCoursesPanel());

        add(tabbedPane);
    }

    private JPanel createCoursePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Text area for displaying registered courses
        courseTextArea = new JTextArea();
        courseTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(courseTextArea);

        // Panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Register Courses"));

        studentIdField = new JTextField();
        JComboBox<Course> courseComboBox = new JComboBox<>();
        courseRegSys.getAvailableCourses().forEach((course) -> {
            courseComboBox.addItem(course);
        });

        inputPanel.add(new JLabel("Student ID:"));
        inputPanel.add(studentIdField);
        inputPanel.add(new JLabel("Choose Course:"));
        inputPanel.add(courseComboBox);

        // Buttons
        JButton registerButton = new JButton("Register Selected Course");
        registerButton.addActionListener((ActionEvent e) -> {
            String studentId = studentIdField.getText();
            Course selectedCourse = (Course) courseComboBox.getSelectedItem();
            if (studentId.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter a Student ID!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (selectedCourse != null) {
                courseRegSys.registerCourse(studentId, selectedCourse);
                courseTextArea.append("Student " + studentId + " registered in course: " + selectedCourse.getCourseName() + "\n");
            }
        });

        // Add components to the panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registerButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }
private JPanel createGradePanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    // Text area for displaying grades
    gradeTextArea = new JTextArea();
    gradeTextArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(gradeTextArea);

    // Panel for input fields
    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components
    inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Grades"));

    // Initialize fields for input
    JTextField studentIdField = new JTextField(15);
    JComboBox<Course> courseComboBox = new JComboBox<>();
    courseRegSys.getAvailableCourses().forEach((course) -> {
        courseComboBox.addItem(course);
        });
    JTextField gradeField = new JTextField(10);

    // Add components to the GridBagLayout
    gbc.gridx = 0; gbc.gridy = 0;
    inputPanel.add(new JLabel("Student ID:"), gbc);
    gbc.gridx = 1; gbc.gridy = 0;
    inputPanel.add(studentIdField, gbc);

    gbc.gridx = 0; gbc.gridy = 1;
    inputPanel.add(new JLabel("Choose Course:"), gbc);
    gbc.gridx = 1; gbc.gridy = 1;
    inputPanel.add(courseComboBox, gbc);

    gbc.gridx = 0; gbc.gridy = 2;
    inputPanel.add(new JLabel("Grade:"), gbc);
    gbc.gridx = 1; gbc.gridy = 2;
    inputPanel.add(gradeField, gbc);

    // Button to save grade
    JButton saveGradeButton = new JButton("Save Grade");
    saveGradeButton.addActionListener((ActionEvent e) -> {
        String studentId = studentIdField.getText();
        Course selectedCourse = (Course) courseComboBox.getSelectedItem();
        String grade = gradeField.getText();
        if (studentId.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Please enter all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (selectedCourse != null) {
            courseRegSys.addGrade(studentId, selectedCourse.getCourseId(), grade);
            gradeTextArea.append("Grade " + grade + " added for student " + studentId + " in course: " + selectedCourse.getCourseName() + "\n");
        }
    });

    // Add components to button panel
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(saveGradeButton);

    // Layout setup
    panel.add(scrollPane, BorderLayout.CENTER);
    panel.add(inputPanel, BorderLayout.NORTH);
    panel.add(buttonPanel, BorderLayout.SOUTH);

    return panel;
}


    private JPanel createViewCoursesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea displayTextArea = new JTextArea();
        displayTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayTextArea);

        // Panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("View Courses and Grades"));

        JTextField studentIdField = new JTextField();
        inputPanel.add(new JLabel("Student ID:"));
        inputPanel.add(studentIdField);

        // Button to display courses and grades
        JButton viewCoursesButton = new JButton("View Courses and Grades");
        viewCoursesButton.addActionListener((ActionEvent e) -> {
            String studentId = studentIdField.getText();
            if (studentId.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter a Student ID!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            StringBuilder result = new StringBuilder();
            try {
                // Get student courses and grades
                List<Course> courses = (List<Course>) courseRegSys.getStudentCourses(studentId);
                if (courses.isEmpty()) {
                    result.append("No courses found for student ").append(studentId);
                } else {
                    result.append("Courses and Grades for student ").append(studentId).append(":\n");
                    courses.forEach((course) -> {
                        String grade = courseRegSys.getGrade(studentId, course.getCourseId());
                        result.append(course.getCourseName()).append(" - Grade: ")
                                .append(grade == null ? "Not yet graded" : grade).append("\n");
                    });
                }
            } catch (Exception ex) {
                result.append("Error fetching courses: ").append(ex.getMessage());
            }
            
            displayTextArea.setText(result.toString());
        });

        // Add components to the panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewCoursesButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentManagementGUI().setVisible(true);
        });
    }
}