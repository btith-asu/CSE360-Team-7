package com.example.cse360;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Delivery extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Delivery.class.getResource("delivery-order.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 852, 480);
        scene.getStylesheets().add ("com/example/cse360/Style.css");
        stage.setTitle("Delivery Details");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}