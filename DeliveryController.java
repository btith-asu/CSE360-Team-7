package com.example.cse360;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class DeliveryController {
    public int timeMinutes;
    private int finishTime;
    private int startTime;
    private int timeElapsed;
    private int updatedTime;
    private int updatedOrders;
    private int ratio;
    private int counter;

    @FXML
    private HBox NavBar;

    @FXML
    private Region navCart;

    @FXML
    private Text ordersAhead;

    @FXML
    private Text timeAhead;

    @FXML
    private Button calTime;

    @FXML
    public int calculateTime(ActionEvent event) {
        DeliveryDetails Orders = new DeliveryDetails();
        startTime();
        ordersAhead.setText(String.valueOf(Orders.orderAhead + " Orders"));
        timeAhead.setText(String.valueOf(Orders.timeArrival + " Minutes"));
        calTime.setDisable(true);
        return 0;
    }
    private long startTime(){
        long start = System.nanoTime();
        startTime = (int) (start/1000000000);
        return startTime;
    }
    private long endTime(){
        long finish = System.nanoTime();
        finishTime = (int) (finish/1000000000);
        return finishTime;
    }

    private int calculateDifference(){
        timeElapsed = (finishTime - startTime);
        timeMinutes = (timeElapsed/60);
        return this.timeMinutes;
    }

    @FXML
    private void updateTime(ActionEvent event) {
        endTime();
        calculateDifference();
        DeliveryDetails Orders = new DeliveryDetails();
        updatedTime = Orders.timeArrival - timeMinutes;
        updatedOrders = Orders.orderAhead;
        ratio = Orders.timeArrival/Orders.orderAhead;
        if (ratio <= (Orders.timeArrival - updatedTime))
            counter = ratio/(Orders.timeArrival - updatedTime);
            updatedOrders = updatedOrders - (counter);
        ordersAhead.setText(String.valueOf(updatedOrders + " Orders"));
        timeAhead.setText(String.valueOf(updatedTime)+ " Minutes");
    }
}
