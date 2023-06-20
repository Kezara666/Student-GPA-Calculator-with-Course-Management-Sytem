package com.example.demo2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private TextField tf_course1;

    @FXML
    private TextField tf_credit1;

    @FXML
    private TextField tf_grade1;

    @FXML
    private Button btn_calculate;

    @FXML
    private Button btn_calculate1;

    @FXML
    private Label lbl_result;

    @FXML
    private TableView<Course> tableView;

    @FXML
    private TableColumn<Course, Integer> idColumn;

    @FXML
    private TableColumn<Course, String> nameColumn;

    @FXML
    private TableColumn<Course, Integer> creditsColumn;

    @FXML
    private TableColumn<Course, String> gradeColumn;

    @FXML
    private TableColumn<Course, Double> gpaColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up the columns in the TableView


        // Set up the event handler for the button click
        btn_calculate.setOnAction(this::handleCalculateButtonClick);
        btn_calculate1.setOnAction(this::handleSum);
    }

    private void handleSum(ActionEvent event){
        ObservableList<Course> courses = tableView.getItems();

        double sum = 0.0;
        int rowCount = courses.size();

        for (Course course : courses) {
            sum += course.getGpa();
        }

        double average = sum / rowCount;
        System.out.println("Sum of GPA: " + sum);
        System.out.println("Average GPA: " + average);
        lbl_result.setText("GPA calculated: " + average);



    }

    private void handleCalculateButtonClick(ActionEvent event) {
        // Get the values from the text fields
        String courseName = tf_course1.getText();
        int credits = Integer.parseInt(tf_credit1.getText());
        String grade = tf_grade1.getText();

        // Perform GPA calculation
        GPACalculator gpaCalculator = new GPACalculator();
        double gradePoints = gpaCalculator.getGradePoints(grade);
        double gpa = gpaCalculator.calculateGPA(courseName, credits, gradePoints);

        new DBOperations().insertDataIntoGpa(0, courseName, credits, grade, gpa);


        // Clear the input fields
        tf_course1.clear();
        tf_credit1.clear();
        tf_grade1.clear();

        // Update the result label
        showDataInTableView();


    }

    public void showDataInTableView() {
        String sql = "SELECT * FROM gpa";

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            ObservableList<Course> courses = FXCollections.observableArrayList();

            while (resultSet.next()) {
                int id = resultSet.getInt("idgpa");
                String name = resultSet.getString("c_name");
                int credits = resultSet.getInt("credit");
                String grade = resultSet.getString("grade");
                double gpa = resultSet.getDouble("gpa");

                Course course = new Course(id, name, credits, grade, gpa);
                courses.add(course);
            }

            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            creditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));
            gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
            gpaColumn.setCellValueFactory(new PropertyValueFactory<>("gpa"));

            tableView.setItems(courses);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
