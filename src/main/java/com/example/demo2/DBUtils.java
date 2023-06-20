package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;


import java.io.IOException;
import java.sql.SQLException;

public class DBUtils {
    public static ActionEvent event;

    public static void changeSceneLogin(ActionEvent event, String fxmlFile, String title) throws IOException {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
        Parent root = loader.load();





        // Create a new scene
        Scene scene = new Scene(root);

        // Get the current stage from the event source
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene on the stage
        currentStage.setScene(scene);
        currentStage.setTitle(title);


        // Show the stage with the new scene
        currentStage.show();


    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/student";
        String username = "root";
        String password = "1234";

        return DriverManager.getConnection(url, username, password);
    }



}
