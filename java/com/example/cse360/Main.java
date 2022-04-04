package com.example.cse360;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    private ArrayList<Order> orderQueue = new ArrayList<Order>();

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Restaurant");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public int getOrderCount() {
        return orderQueue.size();
    }

    public void addOrder(Order newOrder) {
        orderQueue.add(newOrder);
    }

    public void removeOrder() {
        orderQueue.remove(0);
    }
}