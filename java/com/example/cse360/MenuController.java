package com.example.cse360;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MenuController implements Initializable {
    @FXML
    private TextField searchBar;

    @FXML
    private ScrollPane scroll;

    @FXML
    private AnchorPane anchor;

    @FXML
    private VBox vboxOuter;

    @FXML
    private VBox vboxInner;

    @FXML
    private Button checkout;

    @FXML
    private HBox buttonBox;

    private ArrayList<ScrollPane> tabScrolls = new ArrayList<ScrollPane>();;
    private ArrayList<HBox> hboxContain = new ArrayList<HBox>();
    private ArrayList<VBox> vboxContain = new ArrayList<VBox>();
    ArrayList<String> types = new ArrayList<String>();
    ArrayList<Integer> ids = new ArrayList<Integer>();
    ArrayList<Integer> indices = new ArrayList<Integer>();

    private Order order = new Order();
    private Menu menu = new Menu();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupMenu();
    }

    private void setupMenu() {
        try {
            File file = new File("menuItems.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String data = scan.nextLine();
                ArrayList<String> parser = new ArrayList<>(Arrays.asList(data.split("\\\\")));

                MenuItem itemTemp = new MenuItem(Integer.parseInt(parser.get(0)), Integer.parseInt(parser.get(1)),parser.get(2),parser.get(3), Double.parseDouble(parser.get(4)), parser.get(5), parser.get(6));
                menu.addItemMenu(itemTemp);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        VBox spacing = new VBox();
        spacing.setMaxWidth(10);
        this.tabScrolls = new ArrayList<ScrollPane>();
        this.hboxContain = new ArrayList<HBox>();
        this.vboxContain = new ArrayList<VBox>();

        for(int i = 0; i < menu.getCount(); i++) {
            if(types.size() == 0) {
                ScrollPane tempScroll = new ScrollPane();
                tempScroll.setFitToHeight(true);
                tempScroll.setFitToWidth(true);
                HBox tempHBox = new HBox();
                VBox tempVBox = new VBox();

                tempHBox.getChildren().add(spacing);
                tempHBox.getChildren().add(menu.getItemShell(i));
                tempScroll.setMinHeight(330);
                Text tempType = new Text(menu.getItem(i).getType());
                tempType.setFont(new Font("Roboto", 40));
                tempVBox.getChildren().addAll(tempType, tempHBox);

                tempScroll.setContent(tempVBox);
                hboxContain.add(tempHBox);
                vboxContain.add(tempVBox);
                tabScrolls.add(tempScroll);



                types.add(menu.getItem(i).getType());
            }
            else {
                boolean check = false;
                for (int j = 0; j < types.size(); j++) {
                    //System.out.println(types.get(j));
                    if (types.get(j).equals(menu.getItem(i).getType())) {
                        hboxContain.get(j).getChildren().add(menu.getItemShell(i));
                        //System.out.println(menu.getItem(i).getName());
                        check = true;
                    } else if (j == types.size() - 1 && check == false) {
                        ScrollPane tempScroll = new ScrollPane();
                        tempScroll.setFitToHeight(true);
                        tempScroll.setFitToWidth(true);
                        HBox tempHBox = new HBox();
                        VBox tempVBox = new VBox();

                        tempHBox.getChildren().add(spacing);
                        tempHBox.getChildren().add(menu.getItemShell(i));
                        tempScroll.setMinHeight(330);
                        Text tempType = new Text(menu.getItem(i).getType());
                        tempType.setFont(new Font("Roboto", 40));
                        tempVBox.getChildren().addAll(tempType, tempHBox);

                        tempScroll.setContent(tempVBox);
                        hboxContain.add(tempHBox);
                        vboxContain.add(tempVBox);
                        tabScrolls.add(tempScroll);



                        types.add(menu.getItem(i).getType());
                        j++;
                    }
                }
            }
        }
        for(int i = 0; i < types.size(); i++) {
            vboxInner.getChildren().add(tabScrolls.get(i));
        }
        scroll.setContent(vboxInner);
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);
        vboxOuter.setPrefHeight(vboxOuter.getMaxHeight());
        buttonBox.requestFocus();
        /*tabScroll.setFitToHeight(false);
        tabScroll.setFitToWidth(false);
        hboxOuter.getChildren().addAll(spacing, menu.getItemShell(0), menu.getItemShell(1), menu.getItemShell(2), menu.getItemShell(3));
        */

        for(int i = 0; i < menu.getCount(); i++) {
            int index = i;

            menu.getItemImage(i).setPickOnBounds(true);
            menu.getItemImage(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    boolean found = false;
                    if(ids.size() == 0) {
                        order.addItemOrder(menu.getItem(index));
                        indices.add(order.getCount()-1);
                        ids.add(menu.getItem(index).getId());
                    }
                    else {
                        for(int j = 0; j < ids.size(); j++) {
                            if(ids.get(j) == menu.getItem(index).getId()) {
                                /*System.out.println(j);
                                System.out.println(ids.get(j));
                                System.out.println(indices.get(j));*/
                                int k = order.getItem(indices.get(j)).getCount()+1;
                                order.getItem(indices.get(j)).setCount(k);
                                found = true;
                            }
                            else if(j == ids.size()-1 && found == false){
                                System.out.println("?");
                                System.out.println(menu.getItem(index).getId());
                                System.out.println("ID array");
                                System.out.println(ids.get(0));
                                System.out.println("ID size");
                                System.out.println(ids.size());
                                order.addItemOrder(menu.getItem(index));
                                indices.add(order.getCount()-1);
                                ids.add(menu.getItem(index).getId());
                                j = j+2;
                                found = true;
                            }
                        }
                    }

                    //System.out.println(menu.getItem(index).getName());
                }
            });
        }

    }

    @FXML
    public void checkout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("order.fxml"));
        Parent tableViewParent = loader.load();

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene tableViewScene = new Scene(tableViewParent, window.getWidth(), window.getHeight());

        OrderController controller = loader.getController();
        System.out.println(order.getCount());
        controller.orderConstruct(order);

        //window.setWidth(window.getWidth());
        //window.setHeight(window.getHeight());
        window.setScene(tableViewScene);
        window.show();


    }

    @FXML
    public void search() {

        String input = searchBar.getText();

        vboxInner.getChildren().clear();
        for(int i = 0; i < tabScrolls.size(); i++) {
            tabScrolls.get(i).setContent(null);
            hboxContain.get(i).getChildren().removeAll();
            vboxContain.get(i).getChildren().removeAll();
        }
        tabScrolls.clear();
        hboxContain.clear();
        vboxContain.clear();
        types.clear();

        VBox spacing = new VBox();
        spacing.setMaxWidth(10);

        scroll.setContent(null);
        if(input.compareTo("") == 0) {
            for(int i = 0; i < menu.getCount(); i++) {
                if(types.size() == 0) {
                    ScrollPane tempScroll = new ScrollPane();
                    tempScroll.setFitToHeight(true);
                    tempScroll.setFitToWidth(true);
                    HBox tempHBox = new HBox();
                    VBox tempVBox = new VBox();

                    tempHBox.getChildren().add(spacing);
                    tempHBox.getChildren().add(menu.getItemShell(i));
                    tempScroll.setMinHeight(330);
                    Text tempType = new Text(menu.getItem(i).getType());
                    tempType.setFont(new Font("Roboto", 40));
                    tempVBox.getChildren().addAll(tempType, tempHBox);

                    tempScroll.setContent(tempVBox);
                    hboxContain.add(tempHBox);
                    vboxContain.add(tempVBox);
                    tabScrolls.add(tempScroll);

                    types.add(menu.getItem(i).getType());
                }
                else {
                    boolean check = false;
                    for (int j = 0; j < types.size(); j++) {
                        //System.out.println(types.get(j));
                        if (types.get(j).equals(menu.getItem(i).getType())) {
                            hboxContain.get(j).getChildren().add(menu.getItemShell(i));
                            //System.out.println(menu.getItem(i).getName());
                            check = true;
                        } else if (j == types.size() - 1 && check == false) {
                            ScrollPane tempScroll = new ScrollPane();
                            tempScroll.setFitToHeight(true);
                            tempScroll.setFitToWidth(true);
                            HBox tempHBox = new HBox();
                            VBox tempVBox = new VBox();

                            tempHBox.getChildren().add(spacing);
                            tempHBox.getChildren().add(menu.getItemShell(i));
                            tempScroll.setMinHeight(330);
                            Text tempType = new Text(menu.getItem(i).getType());
                            tempType.setFont(new Font("Roboto", 40));
                            tempVBox.getChildren().addAll(tempType, tempHBox);

                            tempScroll.setContent(tempVBox);
                            hboxContain.add(tempHBox);
                            vboxContain.add(tempVBox);
                            tabScrolls.add(tempScroll);
                            types.add(menu.getItem(i).getType());
                            j++;
                        }
                    }
                }
            }
            vboxInner.getChildren().clear();
            for(int i = 0; i < types.size(); i++) {
                vboxInner.getChildren().add(tabScrolls.get(i));
            }
        }
        else {
            for (int i = 0; i < menu.getCount(); i++) {
                boolean clear = false;
                if (input.compareToIgnoreCase(menu.getItem(i).getName()) == 0 || input.compareToIgnoreCase(menu.getItem(i).getType()) == 0) {
                    clear = true;
                }
                else {
                    for(int m = 0; m < menu.getItem(i).tagCount(); m++) {
                        if(input.compareToIgnoreCase(menu.getItem(i).getTags(m)) == 0 && clear == false) {
                            clear = true;
                        }
                    }
                }
                if (clear == true) {
                    if (types.size() == 0) {
                        ScrollPane tempScroll = new ScrollPane();
                        tempScroll.setFitToHeight(true);
                        tempScroll.setFitToWidth(true);
                        HBox tempHBox = new HBox();
                        VBox tempVBox = new VBox();

                        tempHBox.getChildren().add(spacing);
                        tempHBox.getChildren().add(menu.getItemShell(i));
                        tempScroll.setMinHeight(330);
                        Text tempType = new Text(menu.getItem(i).getType());
                        tempType.setFont(new Font("Roboto", 40));
                        tempVBox.getChildren().addAll(tempType, tempHBox);

                        tempScroll.setContent(tempVBox);
                        hboxContain.add(tempHBox);
                        vboxContain.add(tempVBox);
                        tabScrolls.add(tempScroll);


                        types.add(menu.getItem(i).getType());
                    } else {
                        boolean check = false;
                        for (int j = 0; j < types.size(); j++) {
                            //System.out.println(types.get(j));
                            if (types.get(j).equals(menu.getItem(i).getType())) {
                                hboxContain.get(j).getChildren().add(menu.getItemShell(i));
                                //System.out.println(menu.getItem(i).getName());
                                check = true;
                            } else if (j == types.size() - 1 && check == false) {
                                ScrollPane tempScroll = new ScrollPane();
                                tempScroll.setFitToHeight(true);
                                tempScroll.setFitToWidth(true);
                                HBox tempHBox = new HBox();
                                VBox tempVBox = new VBox();

                                tempHBox.getChildren().add(spacing);
                                tempHBox.getChildren().add(menu.getItemShell(i));
                                tempScroll.setMinHeight(330);
                                Text tempType = new Text(menu.getItem(i).getType());
                                tempType.setFont(new Font("Roboto", 40));
                                tempVBox.getChildren().addAll(tempType, tempHBox);

                                tempScroll.setContent(tempVBox);
                                hboxContain.add(tempHBox);
                                vboxContain.add(tempVBox);
                                tabScrolls.add(tempScroll);
                                types.add(menu.getItem(i).getType());
                                j++;
                            }
                        }
                    }
                }
                else {
                    for(int m = 0; m < menu.getItem(i).tagCount(); m++) {

                    }
                }
            }
            vboxInner.getChildren().clear();
            for (int i = 0; i < types.size(); i++) {
                //System.out.println(types.size());
                vboxInner.getChildren().add(tabScrolls.get(i));
            }
        }
        scroll.setContent(null);
        scroll.setContent(vboxInner);
    }

    public void orderConstruct(Order incomeOrder) {
        indices.clear();
        ids.clear();
        order = incomeOrder;
        for(int index = 0; index < order.getCount(); index++) {
            indices.add(index);
            ids.add(order.getItem(index).getId());
            System.out.println(ids.get(index));
            System.out.println(indices.get(index));
        }
    }


}


