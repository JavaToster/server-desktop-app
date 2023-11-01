package com.example;

import java.io.IOException;

import com.example.backend.CheckDB;
import com.example.backend.NamesNow;
import com.example.backend.Updates;
import com.example.connectToServer.GetInfo;
import com.example.connectToServer.UpdatesServer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SingUpController {

    private CheckDB checkDB = new CheckDB();
    
    @FXML
    private TextField inputLastName;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputPassword;

    @FXML
    private TextField inputPhoneNumber;

    @FXML
    private TextField inputUsername;

    @FXML
    private Button logIn;

    @FXML
    private Label messageSingUp;

    @FXML
    private Button singUp;

    @FXML
    private Label singUpLabel;

    @FXML
    void toLogIn(ActionEvent event) {
        try {
            App.setRoot("loginController");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void toSingUp(ActionEvent event) {
        // String username = inputUsername.getText();
        // String name = inputName.getText();
        // String lastName = inputLastName.getText();
        // String password = inputPassword.getText();
        // long phone = 0;
        // try{
        //     phone = Long.parseLong(inputPhoneNumber.getText());
        // } catch (NumberFormatException e){
        //     messageSingUp.setText("Неправильный формат телефона");
        //     inputPhoneNumber.setText("");
        //     return;
        // }

        // if(checkDB.checkUsername(username) || checkDB.checkPhone(phone)){
        //     messageSingUp.setText("Никнейм или номер телефона уже существует");
        //     return;
        // }

        // Updates.setSingUp(username, name, lastName, phone, password);
        // NamesNow.setAllName(username, name, lastName);
        // try {
        //     App.setRoot("mainWindowController");
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        String username = inputUsername.getText();
        String firstname = inputName.getText();
        String lastname = inputLastName.getText();
        String password = inputPassword.getText();
        String phone = inputPhoneNumber.getText();
        try {
            Long.parseLong(inputPhoneNumber.getText());
        } catch (NumberFormatException e) {
            messageSingUp.setText("Неправильный формат телефона");
            inputPhoneNumber.setText("");
            return;
        }

        if(GetInfo.isUsername(username) || GetInfo.isPhone(""+phone)){
            messageSingUp.setText("Такой никнейм или телефон уже существует");
            return;
        } 

        UpdatesServer.singUp(username, firstname, lastname, phone, password);
        NamesNow.setAllName(username, firstname, lastname);
        try {
            App.setRoot("mainWindowController");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
