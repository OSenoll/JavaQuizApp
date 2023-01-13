package com.example.loginpagetry.AdminMenuOperations;

import com.example.loginpagetry.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SyllabusController implements Initializable {
    @FXML
    private Label errorLabel;
    @FXML
    private TableView<SyllabusInfo> Syllabus;
    @FXML
    private TableColumn<SyllabusInfo,String> monday;
    @FXML
    private TableColumn<SyllabusInfo,String> tuesday;
    @FXML
    private TableColumn<SyllabusInfo,String> wednesday;
    @FXML
    private TableColumn<SyllabusInfo,String> thursday;
    @FXML
    private TableColumn<SyllabusInfo,String> friday;
    @FXML
    private TextField mondayTF;
    @FXML
    private TextField tuesdayTF;
    @FXML
    private TextField wednesdayTF;
    @FXML
    private TextField thursdayTF;
    @FXML
    private TextField fridayTF;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        monday.setCellValueFactory(new PropertyValueFactory<SyllabusInfo,String>("monday"));
        tuesday.setCellValueFactory(new PropertyValueFactory<SyllabusInfo,String>("tuesday"));
        wednesday.setCellValueFactory(new PropertyValueFactory<SyllabusInfo,String>("wednesday"));
        thursday.setCellValueFactory(new PropertyValueFactory<SyllabusInfo,String>("thursday"));
        friday.setCellValueFactory(new PropertyValueFactory<SyllabusInfo,String>("friday"));
        try {
            readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void readFile() throws IOException {

        List<SyllabusInfo> syllabusInfoList = new ArrayList<SyllabusInfo>();
        ObservableList<SyllabusInfo> observableList = FXCollections.observableList(syllabusInfoList);
        File sylb = new File("./src/main/java/com/example/loginpagetry/Data/Syllabus.txt");

        if(sylb.exists() == false){
            sylb.createNewFile();
        }

        String line;
        Scanner scanner = new Scanner(sylb);

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] accountData = line.split(";");
            String monday = accountData[0];
            String tuesday = accountData[1];
            String wednesday = accountData[2];
            String thursday = accountData[3];
            String friday = accountData[4];

            SyllabusInfo syllabusInfo = new SyllabusInfo(monday,tuesday,wednesday,thursday,friday);
            observableList.add(syllabusInfo);
            Syllabus.setItems(observableList);
        }
        /*return observableList;*/
    }
    public void addData(){
        if (mondayTF.getText().isEmpty() || tuesdayTF.getText().isEmpty() || wednesdayTF.getText().isEmpty() || thursdayTF.getText().isEmpty() || fridayTF.getText().isEmpty()) {
            errorLabel.setText("Bilgiler bos Bırakılmamalı");
        }
        else {StringBuilder sb = new StringBuilder();
            sb.append(mondayTF.getText());
            sb.append(";");
            sb.append(tuesdayTF.getText());
            sb.append(";");
            sb.append(wednesdayTF.getText());
            sb.append(";");
            sb.append(thursdayTF.getText());
            sb.append(";");
            sb.append(fridayTF.getText());


            String data = new String(sb);
            System.out.println(data);

            BufferedWriter writer = null;
            SceneController sc = new SceneController();
            try {
                writer = new BufferedWriter(new FileWriter("./src/main/java/com/example/loginpagetry/Data/Syllabus.txt", true));
                writer.append(data);
                writer.newLine();
                System.out.println("Dosya yazildi");
                writer.close();
                sc.changeScene("SyllabusOperations.fxml",900,750);
                Syllabus.refresh();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void deleteData() throws IOException {
        SceneController sc = new SceneController();
        SyllabusInfo syllabusinfo = Syllabus.getSelectionModel().getSelectedItem();
        String line;
        StringBuilder sb = new StringBuilder();
        sb.append(syllabusinfo.getMonday());
        sb.append(";");
        sb.append(syllabusinfo.getTuesday());
        sb.append(";");
        sb.append(syllabusinfo.getWednesday());
        sb.append(";");
        sb.append(syllabusinfo.getThursday());
        sb.append(";");
        sb.append(syllabusinfo.getFriday());
        String junk = new String(sb);

        Path source = Paths.get("./src/main/java/com/example/loginpagetry/Data/tempInfo.txt");
        File remove = new File("./src/main/java/com/example/loginpagetry/Data/Syllabus.txt");
        BufferedReader bReader = new BufferedReader(new FileReader(remove));
        BufferedWriter bWriter = new BufferedWriter(new FileWriter("./src/main/java/com/example/loginpagetry/Data/tempInfo.txt"));

        while ((line = bReader.readLine()) != null) {
            if (!line.contentEquals(junk)) {

                bWriter.write(line);
                bWriter.newLine();
            }
        }
        bReader.close();
        bWriter.close();
        remove.delete();
        Files.move(source, source.resolveSibling("Syllabus.txt"));
        sc.changeScene("SyllabusOperations.fxml",900,750);
    }

    public void goBack(ActionEvent event) throws IOException {
        SceneController sc = new SceneController();
        sc.changeScene("AdminMenu.fxml", 900, 750);
    }



}
