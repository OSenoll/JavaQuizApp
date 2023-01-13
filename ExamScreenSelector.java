package com.example.loginpagetry.AdminMenuOperations;

import com.example.loginpagetry.SceneController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExamScreenSelector extends SceneController implements Initializable {

    @FXML
    private ListView<String> myListview;
    @FXML
    private Button deleteExamButton;




    public void initialize(URL url, ResourceBundle resourceBundle){
        myListview.setFixedCellSize(50.0);
        try {
            loadFiles();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void loadFiles() throws IOException {

        String dir = "./src/main/java/com/example/loginpagetry/Data/Exams/";

        File directory = new File(dir);

        File[] files = directory.listFiles();

        for (File file : files) {
            myListview.getItems().add((file.getName()));
        }
        myListview.refresh();
    }



    @FXML
    protected void deletExam() throws IOException {
        String dir = "./com/example/loginpagetry/Data/Exams/";
        ObservableList<String> selectedExam = myListview.getSelectionModel().getSelectedItems();
        for (String fileName : selectedExam) {
            File file = new File(dir + fileName);
            System.out.println(dir+fileName);
            file.delete();
        }
        SceneController m = new SceneController();
        m.changeScene("ExamScreenSelector.fxml", 900, 750);

    }

    public void changeSceneToAddExam(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/com/example/loginpagetry/ExamOperations.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setHeight(750);
        stage.setWidth(900);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public void goBack(ActionEvent event) throws IOException {
        SceneController m = new SceneController();
        m.changeScene("AdminMenu.fxml", 900, 750);
    }
}
