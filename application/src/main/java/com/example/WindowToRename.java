package com.example;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowToRename{

    private static Stage stage;

    public static void newWindow() throws IOException{
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(loadFXML("small"));
        
        stage.setScene(scene);
        stage.setTitle("Изменить");
        stage.setResizable(false);
        stage.showAndWait();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void close(){
        stage.close();
    }

}