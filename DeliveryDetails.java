package com.example.cse360;

import java.util.Random;

public class DeliveryDetails {
    private int timeArrival;
    private int orderAhead;
    private int time;

    public DeliveryDetails() {
    }

    public int getrandomizeTime() {
        Random rand = new Random();
        this.time = rand.nextInt(27);
        return this.time;
    }

    public int getnumOfOrders() {
        this.orderAhead = 5;
        return this.orderAhead;
    }

    public int gettimeArrival() {
        this.timeArrival = this.getnumOfOrders() * this.getrandomizeTime();
        return this.timeArrival;
    }

    public String toString() {
        return "Item{timeArrival=" + this.timeArrival + ", ordersAhead='" + this.orderAhead + "', time='" + this.time + "'}";
    }
}
