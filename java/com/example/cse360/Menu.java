package com.example.cse360;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuItem> itemList;
    private ArrayList<ImageView> itemImageList;
    private ArrayList<Label> itemLabelList;
    private ArrayList<VBox> itemShellList;

    public Menu() {
        this.itemList = new ArrayList<MenuItem>();
        this.itemImageList = new ArrayList<ImageView>();
        //this.itemLabelList = new ArrayList<Label>();
        this.itemShellList = new ArrayList<VBox>();
    }

    public ArrayList<MenuItem> getItemList() {
        return this.itemList;
    }

    public void addItemMenu(MenuItem item) {
        itemList.add(item);
        ImageView tempImage = new ImageView(item.getImage());
        tempImage.setFitHeight(200);
        tempImage.setPreserveRatio(true);
        itemImageList.add(tempImage);
        Text tempLabel = new Text(item.getName());
        tempLabel.setFont(new Font("Roboto", 30));
        //itemLabelList.add(tempLabel);
        VBox tempBorderPane = new VBox();
        tempBorderPane.setAlignment(Pos.CENTER);
        Insets inset = new Insets(10);
        tempBorderPane.setPadding(inset);

        tempBorderPane.getChildren().addAll(tempImage, tempLabel);
        itemShellList.add(tempBorderPane);
    }

    public int removeItemOrder(MenuItem item) {
        for(int i = 0; i < itemList.size(); i++) {
            if(itemList.get(i).getId() == item.getId()) {
                itemList.remove(i);
                return 1;
            }
        }
        return 0;
    }

    public void clearList() {
        itemList.clear();
    }

    public int getCount() {
        return itemList.size();
    }

    public MenuItem getItem(int index) {
        return itemList.get(index);
    }

    public ImageView getItemImage(int index) {
        return itemImageList.get(index);
    }
    /*
    public Label getItemLabel(int index) {
        return itemLabelList.get(index);
    }*/

    public VBox getItemShell(int index) {
        return itemShellList.get(index);
    }
}