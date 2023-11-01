package com.example;

import java.io.IOException;
import java.sql.SQLException;

import com.example.backend.NamesNow;
import com.example.backend.Updates;
import com.example.connectToServer.GetInfo;
import com.example.connectToServer.UpdatesServer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainWindow {
    @FXML
    private Button settingsBtn;

    @FXML
    private Label firstAndLastName;

    @FXML
    private Label history1;

    @FXML
    private Label history10;

    @FXML
    private Label history2;

    @FXML
    private Label history3;

    @FXML
    private Label history4;

    @FXML
    private Button seeFullHistory;

    @FXML
    private Label history5;

    @FXML
    private Label history6;

    @FXML
    private Label history7;

    @FXML
    private Label history8;

    @FXML
    private Label history9;

    @FXML
    private TextField inputText;

    @FXML
    private Button logOut;

    @FXML
    private Label scoreNum;

    @FXML
    private Button topUp;

    @FXML
    private Label username;

    @FXML
    private Button writeOff;

    
    @FXML
    void initialize() throws SQLException{            
        username.setText(NamesNow.getUsername());
        firstAndLastName.setText(NamesNow.getFirstName()+" "+NamesNow.getLastName());
        scoreNum.setText(GetInfo.getScore(NamesNow.getUsername()) + "");
        setHistory();
    }

    @FXML
    void toLogOut(ActionEvent event) {
        try {
            App.setRoot("loginController");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void toTopUp(ActionEvent event) throws SQLException {
        long summa = Long.parseLong(inputText.getText());
        // Updates.addHistoryPlus(NamesNow.getUsername(), summa);
        // Updates.updateScorePlus(NamesNow.getUsername(), summa);
        UpdatesServer.plusScore(NamesNow.getUsername(), summa);
        scoreNum.setText(GetInfo.getScore(NamesNow.getUsername()) + "");
        setHistory();
    }

    @FXML
    void toWriteOff(ActionEvent event) throws SQLException {
        long summa = Long.parseLong(inputText.getText());
        // Updates.addHistoryMinus(NamesNow.getUsername(), summa);
        // Updates.updateScoreMinus(NamesNow.getUsername(), summa);
        UpdatesServer.minusScore(NamesNow.getUsername(), summa);
        scoreNum.setText(GetInfo.getScore(NamesNow.getUsername()) + "");
        setHistory();
    }

    @FXML
    void toSeeFullHistory(ActionEvent event){
        try {
            App.setRoot("FullHistoryController");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void toOpenSettings(ActionEvent event) {
        try {
            App.setRoot("settingsController");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void setHistory() throws SQLException{
        // history1.setText(Updates.getHistory(NamesNow.getUsername())[0]);
        // history2.setText(Updates.getHistory(NamesNow.getUsername())[1]);
        // history3.setText(Updates.getHistory(NamesNow.getUsername())[2]);
        // history4.setText(Updates.getHistory(NamesNow.getUsername())[3]);
        // history5.setText(Updates.getHistory(NamesNow.getUsername())[4]);
        // history6.setText(Updates.getHistory(NamesNow.getUsername())[5]);
        // history7.setText(Updates.getHistory(NamesNow.getUsername())[6]);
        // history8.setText(Updates.getHistory(NamesNow.getUsername())[7]);
        // history9.setText(Updates.getHistory(NamesNow.getUsername())[8]);
        // history10.setText(Updates.getHistory(NamesNow.getUsername())[9]);
        history1.setText(getHistory(GetInfo.getHistory(NamesNow.getUsername()))[0]);
        history2.setText(getHistory(GetInfo.getHistory(NamesNow.getUsername()))[1]);
        history3.setText(getHistory(GetInfo.getHistory(NamesNow.getUsername()))[2]);
        history4.setText(getHistory(GetInfo.getHistory(NamesNow.getUsername()))[3]);
        history5.setText(getHistory(GetInfo.getHistory(NamesNow.getUsername()))[4]);
        history6.setText(getHistory(GetInfo.getHistory(NamesNow.getUsername()))[5]);
        history7.setText(getHistory(GetInfo.getHistory(NamesNow.getUsername()))[6]);
        history8.setText(getHistory(GetInfo.getHistory(NamesNow.getUsername()))[7]);
        history9.setText(getHistory(GetInfo.getHistory(NamesNow.getUsername()))[8]);
        history10.setText(getHistory(GetInfo.getHistory(NamesNow.getUsername()))[9]);
    }

    private String[] getHistory(String[] history){
        String[] returnHistory = new String[10];
        for(int i = 0; i<returnHistory.length; i++){
            if(history[i].equals("null")){
                returnHistory[i] = "";
            }else{
                String[] line = history[i].split(":");
                String summa = line[1];
                String operation;
                if(line[0].equals("0")){
                    operation = "Снятие со счёта";
                }else{
                    operation = "Пополнение счёта";
                }
                
                returnHistory[i] = operation + " - " + summa;
            }
        }

        return returnHistory;
    }
}
