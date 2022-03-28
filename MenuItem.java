package com.example.cse360;
import java.util.Random;

public class MenuItem {

    private String type;
    private final int id;
    private int calories;
    private String name;
    private String price;

    public MenuItem(int calories, String name, String type, String price) {
        this.name = name;
        this.calories = calories;
        this.type = type;
        this.price = price;
        Random rand = new Random();
        this.id = rand.nextInt(1000);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", calories='" + calories + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
