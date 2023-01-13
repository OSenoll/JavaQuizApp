package com.example.loginpagetry.LoginSection;

import com.example.loginpagetry.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;

public class AdminLogin extends LoginInfo {

    @FXML
    private Button adminLogin;
    @FXML
    private Label ErrorLabel;
    @FXML
    private TextField adminUsername;
    @FXML
    private PasswordField adminPassword; // retrieved from login operations

    AdminLogin(TextField adminUsername, PasswordField adminPassword, Label ErrorLabel) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.ErrorLabel = ErrorLabel;
    }

    @Override
    public void Login(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        SceneController m = new SceneController();
        if (adminUsername.getText().equals("o") && adminPassword.getText().equals("1")) {
            ErrorLabel.setText("Giriş Başarılı");
            m.changeScene("AdminMenu.fxml", 900, 750);
        } else if (adminUsername.getText().isEmpty() && adminPassword.getText().isEmpty()) {
            ErrorLabel.setText("Bilgiler boş Bırakılmamalı");
        } else {
            ErrorLabel.setText("Bilgiler Hatalı");
        }
    }


}
