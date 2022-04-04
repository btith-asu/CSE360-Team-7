package com.example.cse360;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DeliveryController implements Initializable {
    public int timeMinutes;
    private int finishTime;
    private int startTime;
    private int timeElapsed;
    private int updatedTime;
    private int updatedOrders;
    private int ratio;
    private int counter;

    //Pane

    @FXML
    private HBox NavBar;

    @FXML
    private Region navCart;

    //Text

    @FXML
    private Text ordersAhead;

    @FXML
    private Text timeAhead;

    //Buttons

    @FXML
    private Button calTime;

    @FXML
    private Button upOrderNum;

    DeliveryDetails Orders;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calTime.setDisable(false);
        upOrderNum.setDisable(true);
    }

    @FXML
    public int calculateTime(ActionEvent event) {
        startTime();
        ordersAhead.setText(String.valueOf(Orders.orderAhead + " Orders"));
        timeAhead.setText(String.valueOf(Orders.timeArrival + " Minutes"));
        calTime.setDisable(true);
        upOrderNum.setDisable(false);
        return 0;
    }

    private long startTime() {
        long start = System.nanoTime();
        startTime = (int) (start / 1000000000);
        return startTime;
    }

    private long endTime() {
        long finish = System.nanoTime();
        finishTime = (int) (finish / 1000000000);
        return finishTime;
    }

    private int calculateDifference() {
        timeElapsed = (finishTime - startTime);
        timeMinutes = (timeElapsed / 60);
        return this.timeMinutes;
    }

    @FXML
    private void updateTime(ActionEvent event) {
        endTime();
        calculateDifference();

        updatedTime = Orders.timeArrival - timeMinutes;
        updatedOrders = Orders.orderAhead;
        ratio = Orders.timeArrival/Orders.orderAhead;
        if (ratio <= (Orders.timeArrival - updatedTime))
            counter = ratio/(Orders.timeArrival - updatedTime);
            updatedOrders = updatedOrders - (counter);
        if (updatedOrders <= 0)
            updatedOrders = 0;
        if(updatedTime == 0){
            ordersAhead.setText(" ");
            timeAhead.setText("Your Order Has Been Delivered!");}
        else{
            ordersAhead.setText(String.valueOf(updatedOrders + " Orders"));
            timeAhead.setText(String.valueOf(updatedTime)+ " Minutes");}
        }

    public void deliverConstruct(int num) {
        Orders = new DeliveryDetails();
    }
}
