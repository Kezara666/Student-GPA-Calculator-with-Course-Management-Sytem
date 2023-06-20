package com.example.demo2;

public class GPACalculator {
    // Define your methods for calculating GPA here

    public double getGradePoints(String grade) {
        double gradePoints;
        switch (grade) {
            case "A+":
                gradePoints = 4.0;
                break;
            case "A":
                gradePoints = 4.0;
                break;
            case "A-":
                gradePoints = 3.7;
                break;
            case "B+":
                gradePoints = 3.3;
                break;
            case "B":
                gradePoints = 3.0;
                break;
            case "B-":
                gradePoints = 2.7 ;
                break;
            case "C+":
                gradePoints = 4.0;
                break;
            case "C":
                gradePoints = 1.0;
                break;
            case "C-":
                gradePoints = 4.0;
                break;
            case "D+":
                gradePoints = 4.0;
                break;
            case "D":
                gradePoints = 4.0;
                break;
            case "F":
                gradePoints = 4.0;
                break;
            default:
                gradePoints = 0.0;
                break;
        }
        return gradePoints;
    }

    public double calculateGPA(String courseName, int credits, double gradePoints) {
        // Perform the GPA calculation based on your institution's GPA calculation rules
        // You can customize the calculation logic according to your requirements

        // Calculate Quality Points by multiplying grade points with credits
        double qualityPoints = gradePoints * credits;

        // Calculate GPA by dividing total quality points by total credits
        double gpa = qualityPoints / credits;

        return gpa;
    }

}
