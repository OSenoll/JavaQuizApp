package com.example.loginpagetry.AdminMenuOperations;

import com.example.loginpagetry.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class AdminMenuController extends SceneController {

    @FXML
    private Button StudentOperations;
    @FXML
    private Button ExamOperations;
    @FXML
    private Button syllabus;
    @FXML
    private Button message;


    public void changeSceneToStudentOperations(ActionEvent event) throws IOException {

        //root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("AdminMenuStudentOperations.fxml"));
        root = FXMLLoader.load(getClass().getResource("/com/example/loginpagetry/AdminMenuStudentOperations.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setHeight(750);
        stage.setWidth(900);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void changeSceneToExamOperations(ActionEvent event) throws IOException {

        //root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("AdminMenuStudentOperations.fxml"));
        root = FXMLLoader.load(getClass().getResource("/com/example/loginpagetry/ExamScreenSelector.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setHeight(750);
        stage.setWidth(900);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void changeSceneToSyllabus(ActionEvent event) throws IOException {

        //root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("AdminMenuStudentOperations.fxml"));
        root = FXMLLoader.load(getClass().getResource("/com/example/loginpagetry/SyllabusOperations.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setHeight(750);
        stage.setWidth(900);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void Logout(ActionEvent event) throws IOException {
        SceneController m = new SceneController();
        m.changeScene("hello-view.fxml", 600, 420);
    }
}
