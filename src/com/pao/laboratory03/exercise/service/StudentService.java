package com.pao.laboratory03.exercise.service;

import com.pao.laboratory03.exercise.exception.StudentNotFoundException;
import com.pao.laboratory03.exercise.model.Student;
import com.pao.laboratory03.exercise.model.Subject;
import java.util.*;

public class StudentService {
    private List<Student> students;
    private static StudentService instance;

    private StudentService() {
        this.students = new ArrayList<>();
    }

    public static StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    public void addStudent(String name, int age) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                throw new RuntimeException("Studentul " + name + " exista deja");
            }
        }
        students.add(new Student(name, age));
    }

    public Student findByName(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        throw new StudentNotFoundException("Studentul " + name + " nu a fost gasit");
    }

    public void addGrade(String studentName, Subject subject, double grade) {
        Student s = findByName(studentName);
        s.addGrade(subject, grade);
    }

    public void printAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Nu exista studenti.");
            return;
        }
        for (Student s : students) {
            System.out.println(s + " Note: " + s.getGrades());
        }
    }

    public void printTopStudents() {
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((s1, s2) -> Double.compare(s2.getAverage(), s1.getAverage()));
        for (Student s : sorted) {
            System.out.println(s);
        }
    }

    public Map<Subject, Double> getAveragePerSubject() {
        Map<Subject, Double> sums = new HashMap<>();
        Map<Subject, Integer> counts = new HashMap<>();

        for (Student s : students) {
            for (Map.Entry<Subject, Double> entry : s.getGrades().entrySet()) {
                Subject sub = entry.getKey();
                double grade = entry.getValue();
                sums.put(sub, sums.getOrDefault(sub, 0.0) + grade);
                counts.put(sub, counts.getOrDefault(sub, 0) + 1);
            }
        }

        Map<Subject, Double> averages = new HashMap<>();
        for (Subject sub : sums.keySet()) {
            averages.put(sub, sums.get(sub) / counts.get(sub));
        }
        return averages;
    }
}