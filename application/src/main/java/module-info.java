module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.json;

    opens com.example to javafx.fxml;
    opens com.example.connectServer to org.json;
    exports com.example;
}
