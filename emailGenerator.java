package com.example.loginpagetry.AdminMenuOperations;

import javafx.scene.control.Label;

import java.io.IOException;

public class emailGenerator {
    private Label ErrorLabel;
    private String password;
    private String email;
    private String emailNumber;
    private int emailNumberLength = 4;
    private int defaultPasswordLength = 8;
    private String schoolSuffix = "nisantasi.edu.tr";

    public emailGenerator(String firstName, String lastName) throws IOException {

        StudentOperations sto = new StudentOperations();

        char firstNameLatter = firstName.charAt(0);

        firstNameLatter = Character.toLowerCase(firstNameLatter);

        this.emailNumber = setEmailNumber(emailNumberLength);

        this.password = randomPassword(defaultPasswordLength);

        email = firstNameLatter + lastName.toLowerCase() + "." + emailNumber + "@" + schoolSuffix;

        sto.GetInfo(email, password, firstName, lastName);


    }


    private String setEmailNumber(int length) {
        String numberSet = "123456789";
        char[] emailNumber = new char[length];
        for (int i = 0; i < length; i++) {
            int rand = (int) (Math.random() * numberSet.length());
            emailNumber[i] = numberSet.charAt(rand);
        }
        return new String(emailNumber);
    }

    private String randomPassword(int length) {
        String passwordSet = "ABCDEFGHIJKLMNOPRSTUVYZabcdefghijklmnoprstuvyz";
        char[] password = new char[length];
        for (int i = 0; i < length; i++) {
            int rand = (int) (Math.random() * passwordSet.length());
            password[i] = passwordSet.charAt(rand);
        }
        return new String(password);
    }
   /* private static void writeUsingFileWriter(HashMap<String,String> Info) {
        File file = new File("StudentInfoFile.txt");
        FileWriter fr = null;
    }*/

}
