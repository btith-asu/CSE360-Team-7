package com.example.cse360;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class DeliveryController {

    @FXML
    private HBox NavBar;

    @FXML
    private Region navCart;

    @FXML
    private Text ordersAhead;

    @FXML
    private Text timeAhead;

    @FXML
    private void calculateTime(ActionEvent event) {
        DeliveryDetails Orders = new DeliveryDetails();
        Orders.getnumOfOrders();
        ordersAhead.setText(String.valueOf(Orders.getnumOfOrders() + " Orders"));
        timeAhead.setText(String.valueOf(Orders.gettimeArrival() + " Minutes"));
    }
}
