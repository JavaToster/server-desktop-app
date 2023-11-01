package com.example;

import com.example.backend.NamesNow;
import com.example.backend.Updates;
import com.example.connectToServer.UpdatesServer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SmallController {

    private static boolean x;

    @FXML
    private Button remameBtn;

    @FXML
    private TextField textField;

    @FXML
    void toRename(ActionEvent event) {
        if(x){
            NamesNow.setName(textField.getText());
            UpdatesServer.reName(NamesNow.getUsername(), textField.getText());
        }
        else{
            NamesNow.setSurname(textField.getText());
            UpdatesServer.reSurname(NamesNow.getUsername(), textField.getText());
        }

        WindowToRename.close();
    }

    public static void setX(boolean _x){
        x = _x;
    }


}
