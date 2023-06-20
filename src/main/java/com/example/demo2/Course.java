package com.example.demo2;

public class Course {
    private int id;
    private String name;
    private int credits;
    private String grade;
    private double gpa;

    // Create constructors, getters, and setters for the class


    public Course(int id, String name, int credits, String grade, double gpa) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.grade = grade;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public String getGrade() {
        return grade;
    }

    public double getGpa() {
        return gpa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
