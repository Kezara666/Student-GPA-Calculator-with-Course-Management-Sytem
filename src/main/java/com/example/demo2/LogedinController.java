package com.example.demo2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class LogedinController implements Initializable {

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_signup;

    @FXML
    private TextField tf_uname;
    @FXML
    private TextField tf_password;

    @FXML
    private Label lbl_error;

    @FXML
    private ProgressIndicator progressIndicator;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
                try {
                    String fxmlFile = "/com/example/demo2/sign-up.fxml";
                    String title = "Sign Up";

                    DBUtils.changeSceneLogin(event, fxmlFile, title);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        btn_login.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent event) {
                try {
                    String role = new DBOperations().Authention_Redirect(tf_uname.getText(), tf_password.getText());
                    if (role.equals("not User")) {
                        lbl_error.setText("You entered credentials not valid");
                    }
                    else if (role.equals("admin")) {
                        animateProgressIndicator("/com/example/demo2/user.fxml");
                        DBUtils.event=event;
                        lbl_error.setText("You entered credentials for user");
                        // Start the animation

                    }
                    if (role.equals("user")) {
                        animateProgressIndicator("/com/example/demo2/admin.fxml");
                        DBUtils.event=event;
                        lbl_error.setText("You entered as admin");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void animateProgressIndicator(String fxml) {
        // Set initial value and duration for the animation
        double initialValue = 0.0;
        double finalValue = 1.0;
        Duration duration = Duration.seconds(4); // Adjust the duration as needed

        // Calculate the number of intermediate steps
        int numSteps = 100; // Adjust the number of steps as needed
        double stepSize = (finalValue - initialValue) / numSteps;

        // Create a Timeline with KeyFrames to update the progress value
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, event -> progressIndicator.setProgress(initialValue)));

        // Add intermediate steps
        for (int i = 1; i <= numSteps; i++) {
            final double value = initialValue + (stepSize * i);
            final Duration stepDuration = duration.divide(numSteps);
            timeline.getKeyFrames().add(new KeyFrame(stepDuration.multiply(i), event -> progressIndicator.setProgress(value)));
        }

        // Add an event handler to the last KeyFrame
        timeline.getKeyFrames().add(new KeyFrame(duration, event -> {
            progressIndicator.setProgress(finalValue);
            if(progressIndicator.getProgress()==1.0){

                System.out.println("Animation complete. Performing additional action.");
                String fxmlFile = fxml;
                String title = "Sign Up";
                try {
                    DBUtils.changeSceneLogin(DBUtils.event, fxmlFile, title);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                progressIndicator.setProgress(0.0);


            }


            ///////////////////////////////////////////////////////////////////////////////////////////////////



            // Perform the desired action


            // TODO: Add your code here to perform the desired action after the animation
        }));

        // Start the animation
        timeline.play();
    }


}
