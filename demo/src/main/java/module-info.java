module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.facade;
    exports com.example.demo.model;
    opens com.example.demo.facade to javafx.fxml;
}