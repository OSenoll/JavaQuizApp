package com.example.loginpagetry.LoginSection;

import com.example.loginpagetry.HelloApplication;
import com.example.loginpagetry.SceneController;
import com.example.loginpagetry.StudentMenuOperations.ActiveUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class StudentLogin extends LoginInfo {

    @FXML
    private Button studentLogin;
    @FXML
    private Label ErrorLabel;
    @FXML
    private TextField studentUsername;
    @FXML
    private PasswordField studentPassword;

    StudentLogin(TextField studentUsername, PasswordField studentPassword, Label ErrorLabel) {
        this.studentUsername = studentUsername;
        this.studentPassword = studentPassword;
        this.ErrorLabel = ErrorLabel;
    }

    @Override
    public void Login(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {

        SceneController m = new SceneController();
        File Login = new File("./src/main/java/com/example/loginpagetry/Data/StudentInfo.txt");
        String line;
        Scanner scanner = new Scanner(Login);

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] accountData = line.split(";");
            String email = accountData[2];
            String password = accountData[3];

            if (studentUsername.getText().equals(email) && studentPassword.getText().equals(password)) {
                ErrorLabel.setText("Giriş Başarılı");
                m.changeScene("StudentMenu.fxml", 900, 750);
                ActiveUser activeUser = ActiveUser.getInstance();
                activeUser.setEmail(email);
                activeUser.setPassword(password);

            } else if (studentUsername.getText().isEmpty() && studentPassword.getText().isEmpty()) {
                ErrorLabel.setText("Bilgiler boş Bırakılmamalı");
            } else {
                ErrorLabel.setText("Bilgiler Hatalı");
            }
        }


    }
}
