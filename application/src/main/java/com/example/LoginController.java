package com.example;

import java.io.IOException;
import java.sql.SQLException;

import com.example.backend.CheckDB;
import com.example.backend.NamesNow;
import com.example.backend.Updates;
import com.example.connectServer.GetInformation;
import com.example.connectToServer.GetInfo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

//    private CheckDB checkDB = new CheckDB();

    @FXML
    private Button logIn;

    @FXML
    private TextField loginInput;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField passwordInput;

    @FXML
    private Button singUp;

    @FXML
    void toLogIn(ActionEvent event) throws NumberFormatException, SQLException {
        // String login = loginInput.getText();
        // String password = passwordInput.getText();
        // if(checkDB.checkIsPhone(login)){
        //     if(checkDB.checkPhone(Long.parseLong(login)) && checkDB.checkPassword(password)){
        //         NamesNow.setAllName(Updates.getUsernameFromPhone(Long.parseLong(login)), Updates.getNameFromPhone(Long.parseLong(login)), Updates.getSurnameFromPhone(Long.parseLong(login)));
        //         try {
        //             App.setRoot("mainWindowController");
        //         } catch (IOException e) {
        //             // TODO Auto-generated catch block
        //             messageLabel.setText("Какие то неполадки");
        //         }
        //     }
        //     else{
        //         passwordInput.setText("");
        //         messageLabel.setText("Неправильный логин или пароль");
        //         return;
        //     }
        // }
        // else{
        //     if(checkDB.checkUsername(login) && checkDB.checkPassword(password)){
        //         NamesNow.setAllName(login, Updates.getNameFromUsername(login), Updates.getSurnameFromUsername(login));
        //         try {
        //             App.setRoot("mainWindowController");
        //         } catch (IOException e) {
        //             // TODO Auto-generated catch block
        //             messageLabel.setText("Какие то неполадки");
        //         }
        //     }
        //     else{
        //         passwordInput.setText("");
        //         messageLabel.setText("Неправильный логин или пароль");
        //         return;
        //     }
        // }

        String loginFromUser = loginInput.getText();
        String passwordFromUser = passwordInput.getText();
        if(GetInfo.checkLoginPhone(loginFromUser)){
            if(GetInfo.isPhone(loginFromUser)){
                if(GetInfo.checkPasswordFromPhone(loginFromUser, passwordFromUser)){
                    NamesNow.setAllName(GetInfo.getUsernameFromPhone(loginFromUser), GetInfo.getFirstnameFromPhone(loginFromUser), GetInfo.getSurnameFromPhone(loginFromUser));
                    try {
                        App.setRoot("mainWindowController");
                        return;
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else{
                    messageLabel.setText("Неправильный пароль");
                    passwordInput.setText("");
                }
            }else{
                messageLabel.setText("Неправильный логин");
            }
        }else{
            if(GetInfo.isUsername(loginFromUser)){
                if(GetInfo.checkPasswordFromUsername(loginFromUser, passwordFromUser)){
                    NamesNow.setAllName(loginFromUser, GetInfo.getFirstname(loginFromUser), GetInfo.getSurname(loginFromUser));
                    try {
                        App.setRoot("mainWindowController");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                else{
                    messageLabel.setText("Неправильный пароль");
                    passwordInput.setText("");
                }
            }else{
                messageLabel.setText("Неправильный логин");
            }
        }

    }

    @FXML
    void toSingUp(ActionEvent event) {
        try {
            App.setRoot("singUpController");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
