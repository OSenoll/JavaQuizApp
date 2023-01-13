package com.example.loginpagetry.LoginSection;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class LoginManager {
    @FXML
    public LoginInfo InfoManager;

    @FXML
    public void LoginFinal(ActionEvent event) throws IOException {
        InfoManager.Login(event);
    }
}
