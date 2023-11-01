package com.example;

import java.io.IOException;
import java.sql.SQLException;

import com.example.backend.NamesNow;
import com.example.backend.Updates;
import com.example.connectToServer.UpdatesServer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class SettingsController {

    @FXML
    private Label labelUsername;

    @FXML
    private Button backBtn;

    @FXML
    private MenuItem deleteAccount;

    @FXML
    private MenuItem clearHistory;

    @FXML
    private MenuButton scoreParametrsBtn;

    @FXML
    private MenuItem zeroScore;

    @FXML
    private MenuButton parametrsBtn;

    @FXML
    private MenuItem deleteLastOperation;

    @FXML
    private MenuItem renameName;

    @FXML
    private MenuItem renameSurname;

    @FXML
    void initialize(){
        labelUsername.setText(NamesNow.getUsername());
    }

    @FXML
    void toBack(ActionEvent event) {
        try {
            App.setRoot("mainWindowController");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void toDeleteAccount(ActionEvent event) {
        UpdatesServer.deleteAccountFromUsername(NamesNow.getUsername());
        try {
            App.setRoot("loginController");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void toDeleteLastOperation(ActionEvent event) throws SQLException {
        UpdatesServer.deleteLastOperation(NamesNow.getUsername());
    }

    @FXML
    void toReName(ActionEvent event) throws IOException {
        SmallController.setX(true);
        WindowToRename.newWindow();
        
    }

    @FXML
    void toReSurname(ActionEvent event) throws IOException {
        SmallController.setX(false);
        WindowToRename.newWindow();
    }

    @FXML
    void toZeroScore(ActionEvent event) {
        UpdatesServer.toZeroScore(NamesNow.getUsername());
    }

    @FXML
    void toClearHistory(ActionEvent event) {
        UpdatesServer.clearHistory(NamesNow.getUsername());
    }
}
