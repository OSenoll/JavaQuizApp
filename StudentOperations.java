package com.example.loginpagetry.AdminMenuOperations;

import com.example.loginpagetry.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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


public class StudentOperations extends goBackScene implements Initializable {

    SceneController sc = new SceneController();
    @FXML
    protected Label ErrorLabel;
    @FXML
    protected TextField name;
    @FXML
    protected TextField surname;
    private String email;
    private String password;

    //-----------------------------
    @FXML
    public TableView<StudentInfo> tableView;
    @FXML
    public TableColumn<StudentInfo, String> nameTw;
    @FXML
    public TableColumn<StudentInfo, String> lastNameTw;
    @FXML
    public TableColumn<StudentInfo, String> emailTw;
    @FXML
    public TableColumn<StudentInfo, String> passwordTw;


    public void initialize(URL url, ResourceBundle resourceBundle) {


        nameTw.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("name"));
        lastNameTw.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("lastName"));
        emailTw.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("email"));
        passwordTw.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("password"));

        try {
            tableView.setItems(readFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
      ObservableList readFile() throws IOException {
         List<StudentInfo> studentList = new ArrayList<StudentInfo>();
         ObservableList<StudentInfo> observableList = FXCollections.observableList(studentList);
         File Login = new File("./src/main/java/com/example/loginpagetry/Data/StudentInfo.txt");

         if(Login.exists()==false){
             Login.createNewFile();
         }
         String line;
        Scanner scanner = new Scanner(Login);

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] accountData = line.split(";");
            String name = accountData[0];
            String lastName = accountData[1];
            String email = accountData[2];
            String password = accountData[3];

            StudentInfo student = new StudentInfo(name, lastName, email, password);
            observableList.add(student);
        }
        return observableList;
    }
    public void callItem(ActionEvent event) throws IOException {

        if (name.getText().isEmpty() || surname.getText().isEmpty()) {
            ErrorLabel.setText("Bilgiler bos Bırakılmamalı");
        }
        else {
            String name_text = name.getText();
            String surname_text = surname.getText();
            emailGenerator em1 = new emailGenerator(name_text, surname_text);
        }

    }

    public void GetInfo(String email, String password, String firstName, String lastName) throws IOException {
        this.email = email;
        this.password = password;
        addItem(email, password, firstName, lastName);
    }

    public void addItem(String email, String password, String firstName, String lastName) throws FileNotFoundException {

        /*System.out.println(email);
        System.out.println(password);
        System.out.println(firstName);
        System.out.println(lastName);*/

        StringBuilder sb = new StringBuilder();
        sb.append(firstName);
        sb.append(";");
        sb.append(lastName);
        sb.append(";");
        sb.append(email);
        sb.append(";");
        sb.append(password);

        String data = new String(sb);
        System.out.println(data);


        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("./src/main/java/com/example/loginpagetry/Data/StudentInfo.txt", true));
            writer.append(data);
            writer.newLine();
            System.out.println("Dosya yazildi");
            writer.close();
            sc.changeScene("AdminMenuStudentOperations.fxml",900,750);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void removeStudent() throws IOException {
        StudentInfo studentInfo = tableView.getSelectionModel().getSelectedItem();
        String line;
        StringBuilder sb = new StringBuilder();
        sb.append(studentInfo.getName());
        sb.append(";");
        sb.append(studentInfo.getLastName());
        sb.append(";");
        sb.append(studentInfo.getEmail());
        sb.append(";");
        sb.append(studentInfo.getPassword());
        String junk = new String(sb);

        Path source = Paths.get("./src/main/java/com/example/loginpagetry/Data/tempInfo.txt");
        File remove = new File("./src/main/java/com/example/loginpagetry/Data/StudentInfo.txt");
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
        Files.move(source, source.resolveSibling("StudentInfo.txt"));
        sc.changeScene("AdminMenuStudentOperations.fxml",900,750);
    }
    @Override
    public void goBack(ActionEvent event) throws IOException {
        sc.changeScene("AdminMenu.fxml", 900, 750);
    }
}
