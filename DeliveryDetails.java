package com.example.cse360;

import java.util.Random;

public class DeliveryDetails {
    public int timeArrival = 2;
    public int orderAhead = 2;
    private int time;
    public int updateTimeArrival;

    public DeliveryDetails() {
    }

    public int getrandomizeTime() {
        Random rand = new Random();
        this.time = rand.nextInt(25);
        return this.time;
    }

    public int gettimeArrival() {
        this.timeArrival = orderAhead * this.getrandomizeTime();
        return this.timeArrival;
    }

    public int updateOrderTime(){
        DeliveryController newTime = new DeliveryController();
        int timeMinutes = newTime.timeMinutes;
        updateTimeArrival = timeArrival - timeMinutes;
        return this.updateTimeArrival;
    }

    public String toString() {
        return "Item{timeArrival=" + this.timeArrival + ", ordersAhead='" + this.orderAhead + "', time='" + this.time + "', time='\" + this.time + \"'}";
    }
}
