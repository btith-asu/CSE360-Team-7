module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cse360 to javafx.fxml;
    exports com.example.cse360;
}