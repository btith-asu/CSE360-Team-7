package com.example.cse360;

import javafx.scene.image.Image;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MenuItem {

    private String type;
    private final int id;
    private int calories;
    private String name;
    private double price;
    private String priceString;
    private String imageLink;
    private Image image;
    private String tag;
    private ArrayList<String> tags = new ArrayList<String>();
    private int count = 1;

    public MenuItem(int id, int calories, String name, String type, double price, String image, String tag) {
        DecimalFormat df = new DecimalFormat("0.00");
        this.name = name;
        this.calories = calories;
        this.type = type;
        this.price = price;
        this.priceString = "$" + df.format(price);
        this.id = id;
        this.imageLink = image;
        this.image = new Image(image);
        this.tag = tag;
        addTags(tag);
    }

    public MenuItem(int calories, String name, String type, double price) {
        DecimalFormat df = new DecimalFormat("0.00");
        this.name = name;
        this.calories = calories;
        this.type = type;
        this.price = price;
        this.priceString = "$" + df.format(price);
        Random rand = new Random();
        this.id = rand.nextInt(1000);

    }

    public MenuItem(int calories, String name, String type, String price) {
        this.name = name;
        this.calories = calories;
        this.type = type;
        this.price = Double.parseDouble(price.replace("$", ""));
        this.priceString = price;
        Random rand = new Random();
        this.id = rand.nextInt(1000);
    }

    public MenuItem(int calories, String name, String type, double price, String image) {
        DecimalFormat df = new DecimalFormat("0.00");
        this.name = name;
        this.calories = calories;
        this.type = type;
        this.price = price;
        this.priceString = "$" + df.format(price);
        Random rand = new Random();
        this.id = rand.nextInt(1000);
        this.image = new Image(image);
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

    public double getPrice() {
        return price * count;
    }

    public String getPriceString() {
        return priceString;
    }

    public Image getImage() { return image; }

    public String getImageLink() { return imageLink; }

    public void setImageLink(String link) {
        this.image = new Image(link);
        this.imageLink = link;
    }

    public void setPrice(double price) {
        DecimalFormat df = new DecimalFormat("0.00");
        this.price = price;
        this.priceString = "$" + df.format(price);
    }

    public void setPriceString(String price) {
        this.price = Double.parseDouble(price.replace("$", ""));
        this.priceString = price;
    }

    public void setTag(String input) {
        this.tag = input;
        tags.clear();
        addTags(input);
    }

    public void addTags(String input) {
        ArrayList<String> parser = new ArrayList<>(Arrays.asList(input.split(", ")));
        for(int i=0; i<parser.size(); i++) {
            this.tags.add(parser.get(i));
        }
    }

    public int deleteTag(String input) {
        for(int i = 0; i < tags.size(); i++) {
            if(tags.get(i).equals(input)) {
                tags.remove(i);
                return 1;
            }
        }
        return 0;
    }

    public void clearTags() {
        this.tags.clear();
    }

    public int tagCount() {
        return tags.size();
    }

    public String getTag() {
        return tag;
    }

    public String getTags(int index) {
        return tags.get(index);
    }

    public void setCount(int num) {
        DecimalFormat df = new DecimalFormat("0.00");
        this.count = num;
        this.priceString = "$" + df.format(price * count);
    }

    public int getCount() {
        return this.count;
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

    public String storeString() {
        return id + "\\" + calories + "\\" + name + "\\" +
                type + "\\" + price + "\\" + imageLink + "\\" + tag +"\n";
    }
}
