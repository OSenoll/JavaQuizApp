package com.example.loginpagetry.LoginSection;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.IOException;

public class LoginOperations {
    @FXML
    private TextField studentUsername;
    @FXML
    private PasswordField studentPassword;
    @FXML
    private TextField adminUsername;
    @FXML
    private PasswordField adminPassword;
    @FXML
    private Label ErrorLabel;

    public void AdminLoginFinal(ActionEvent event) throws IOException {
        LoginManager loginManager = new LoginManager();
        loginManager.InfoManager = new AdminLogin(adminUsername, adminPassword, ErrorLabel);
        loginManager.LoginFinal(event);
    }

    public void StudentLoginFinal(ActionEvent event) throws IOException {
        LoginManager loginManager = new LoginManager();
        loginManager.InfoManager = new StudentLogin(studentUsername, studentPassword, ErrorLabel);
        loginManager.LoginFinal(event);
    }
}
