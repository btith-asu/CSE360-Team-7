package com.example.cse360;

import java.util.ArrayList;

public class Order {
    private ArrayList<MenuItem> itemsOrdered;

    public Order() {
        this.itemsOrdered = new ArrayList<MenuItem>();
    }

    public ArrayList<MenuItem> getItemsOrdered() {
        return this.itemsOrdered;
    }

    public double totalPrice() {
        double sum = 0;
        for(int i = 0; i < itemsOrdered.size(); i++) {
            sum += itemsOrdered.get(i).getPrice();
        }
        return sum;
    }

    public void addItemOrder(MenuItem item) {
        itemsOrdered.add(item);
    }

    public int removeItemOrder(MenuItem item) {
        for(int i = 0; i < itemsOrdered.size(); i++) {
            if(itemsOrdered.get(i).getId() == item.getId()) {
                itemsOrdered.remove(i);
                return 1;
            }
        }
        return 0;
    }

    public void clearOrder() {
        itemsOrdered.clear();
    }

    public int getCount() {
        return itemsOrdered.size();
    }

    public MenuItem getItem(int index) {
        return itemsOrdered.get(index);
    }
}