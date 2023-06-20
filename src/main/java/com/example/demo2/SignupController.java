package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
    @FXML
    private Button btn_signup_s;

    @FXML
    private Button btn_signup_l;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        btn_signup_l.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
                try {
                    String fxmlFile = "/com/example/demo2/login.fxml";
                    String title = "Sign Up";

                    DBUtils.changeSceneLogin(event, fxmlFile, title);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
