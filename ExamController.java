package com.example.loginpagetry.AdminMenuOperations;

import com.example.loginpagetry.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ExamController {
    @FXML
    private Label errorLabel;
    @FXML
    private Label succesLabel;
    @FXML
    private TextField op1;
    @FXML
    private TextField op2;
    @FXML
    private TextField op3;
    @FXML
    private TextField op4;
    @FXML
    private TextField question;
    @FXML
    private RadioButton midTermButton;
    @FXML
    private RadioButton finalButton;
    @FXML
    private RadioButton option1;
    @FXML
    private RadioButton option2;
    @FXML
    private RadioButton option3;
    @FXML
    private RadioButton option4;
    private ToggleGroup radioGroup;
    private ToggleGroup QuizType;
    @FXML
    private Button setQuizTitle;
    @FXML
    private TextField quizTitleField;
    private String title = null;
    private HashMap<String,String[]> questions = new HashMap<>();



    public void initialize() {
        radioButtonSetup();
    }

    private void radioButtonSetup() {
        QuizType = new ToggleGroup();
        radioGroup = new ToggleGroup();
        option1.setToggleGroup(radioGroup);
        option2.setToggleGroup(radioGroup);
        option3.setToggleGroup(radioGroup);
        option4.setToggleGroup(radioGroup);
        midTermButton.setToggleGroup(QuizType);
        finalButton.setToggleGroup(QuizType);
    }

    public void setQuizTitleButton() {

        if (quizTitleField.getText().trim().isEmpty() || QuizType.equals(null)) {
            System.out.println("Geçerli bir sınav adı giriniz ve sınav türünü seciniz.");
        }
        else {
            System.out.println("Sınav adı kaydedildi.");
            title = quizTitleField.getText();
            quizTitleField.setEditable(false);
            this.title = title;
        }
        System.out.println(title);
    }

    private boolean validateFields() {
        String question = this.question.getText();
        String op1 = this.op1.getText();
        String op2 = this.op2.getText();
        String op3 = this.op3.getText();
        String op4 = this.op4.getText();
        Toggle selectedRadio = radioGroup.getSelectedToggle();

        if (question.trim().isEmpty() || op1.trim().isEmpty() || op2.trim().isEmpty() || op3.trim().isEmpty() || op4.trim().isEmpty()) {
            errorLabel.setText("Bilgiler bos bırakılmamalı");
            return false;
        }
        else if(title == null){
            errorLabel.setText("Sınav adı boş bırakılmamalı");
            return false;
        } else if (QuizType ==null){
            errorLabel.setText("Sınav turu bos bırakılmamalı");
            return false;
        } else if(selectedRadio == null){

            errorLabel.setText("Doğru şıkkın yanındaki buton seçilmeli");
            return false;
        }
        else
        {
            return true;
        }


    }

    public void addNextQuestion(ActionEvent event) throws IOException {
        boolean valid = validateFields();
        if (valid) {
            String[] data = new String[6];
            data[0] = op1.getText().trim();
            data[1] = op2.getText().trim();
            data[2] = op3.getText().trim();
            data[3] = op4.getText().trim();

            Toggle selected = radioGroup.getSelectedToggle();
            Toggle selected2 = QuizType.getSelectedToggle();

            if(selected == option1){
                data[4] = op1.getText().trim();
            }
            else if (selected == option2){
                data[4] = op2.getText().trim();
            }
            else if (selected == option3){
                data[4] = op3.getText().trim();
            }
            else if (selected == option4){
                data[4] = op4.getText().trim();
            }

            if(selected2==midTermButton){
                data[5] = "midTerm";
            } else if (selected2==finalButton) {
                data[5] = "final";
            }
            questions.put(question.getText().trim(),data);
            question.clear();
            op1.clear();
            op2.clear();
            op3.clear();
            op4.clear();
            System.out.println("veriler Hashmape kaydedildi");

           BufferedWriter writer = null;
                try {
                writer = new BufferedWriter(new FileWriter("./src/main/java/com/example/loginpagetry/Data/Exams/"+title+ ".txt", true));
               for (Map.Entry<String,String[]> entry : questions.entrySet()){
                   String key = entry.getKey();
                   String[] values = entry.getValue();
                   StringBuilder sb = new StringBuilder();
                   sb.append(key).append(";");
                    for (String value : values){
                        sb.append(value).append(";");
                    }
                    writer.write(sb.toString());
                    writer.newLine();

               }
                questions.clear();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }





            /*Set<String> keySet = questions.keySet();
            Iterator<String> itr = keySet.iterator();*/

            /*if(quiz.exists()){
                System.out.println("File exists");
            }
            else{
                System.out.println("File does not exist");
                BufferedWriter writer = null;
                writer = new BufferedWriter(new FileWriter("/Users/omer/IdeaProjects/LoginPageTRY/src/main/java/com/example/loginpagetry/Data/writeQuiz.txt", true));
                writer.append("Testing");
                writer.close();
            }*/



            /*while (itr.hasNext()){
                String qu = itr.next();
                String[] values = questions.get(qu);
                System.out.println(qu);
                for (String s : values){
                    */
            /*System.out.println(s);*//*

                    BufferedWriter writer = null;
                    try {
                        writer = new BufferedWriter(new FileWriter("/Users/omer/IdeaProjects/LoginPageTRY/src/main/java/com/example/loginpagetry/Data/writeQuiz.txt", true));
                        writer.newLine(); //Dosya yoksa boş satırla başladığı için sıkıntı çıkartıyor. /Düzeltildi.
                        writer.append(s);
                        writer.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }


            }*/
        }

    }

    public void submitQuiz() {
        errorLabel.setText("Sınav Başarı ile Kaydedildi.");
        quizTitleField.clear();
        question.clear();
        op1.clear();
        op2.clear();
        op3.clear();
        op4.clear();
        quizTitleField.setEditable(true);
    }

    public void goBack(ActionEvent event) throws IOException {
        SceneController m = new SceneController();
        m.changeScene("AdminMenu.fxml", 900, 750);
    }
}
