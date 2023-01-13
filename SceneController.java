package com.example.loginpagetry;

import com.example.loginpagetry.LoginSection.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.loginpagetry.HelloApplication.stg;

public class SceneController extends LoginOperations {
    @FXML
    private Button StudentOperations;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField adminPassword;
    @FXML
    private Button studentLogin;
    @FXML
    private Button adminLogin;
    @FXML
    private Button SwitchToAdminButton;
    @FXML
    private Button SwitchToStudentButton;

    protected Stage stage;
    protected Scene scene;
    protected Parent root;

    public void switchToAdminLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchToStudentLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void changeScene(String fxml, int a, int b) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.setWidth(a);
        stg.setHeight(b);
        stg.getScene().setRoot(pane);
    }

}
