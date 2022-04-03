package com.example.cse360;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

import java.net.URL;
import java.util.ResourceBundle;

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


    private ArrayList<ScrollPane> tabScrolls = new ArrayList<ScrollPane>();;
    private ArrayList<HBox> hboxContain = new ArrayList<HBox>();
    private ArrayList<VBox> vboxContain = new ArrayList<VBox>();
    ArrayList<String> types = new ArrayList<String>();

    private Order order = new Order();
    private Menu menu = new Menu();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupMenu();
    }

    private void setupMenu() {
        MenuItem item1 = new MenuItem(400,"Fries","Side", 5.35, "https://media.istockphoto.com/photos/fast-food-picture-id531189325?b=1&k=20&m=531189325&s=170667a&w=0&h=AwqBLMunJMm4BZLGNB2VmeCaFsM0zU_U3HrUnUH3xRk=");
        MenuItem item2 = new MenuItem(800,"Bacon Burger","Entree", 8.95, "https://media.istockphoto.com/photos/western-barbecue-bacon-cheeseburger-picture-id923270448?k=20&m=923270448&s=612x612&w=0&h=jkXjSmzPEw1rv7nMfJnvZCiCuuXWsOYAdoo0RQ9Wb60=");
        MenuItem item3 = new MenuItem(350,"Vanilla Shake","Dessert", 4.50, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXCK3wqAmpLRchnr8KCj1nUQcgnz-yh8A2Lg&usqp=CAU");
        MenuItem item4 = new MenuItem(180,"Sweet Tea","Drink", 2.15, "https://media.istockphoto.com/photos/a-glass-of-frozen-lemon-black-tea-on-a-white-background-picture-id1155623658?k=20&m=1155623658&s=612x612&w=0&h=NENT_Yd2LhoeJJfHHRUUsVjIONkoIgOe8-R19eC8qxg=");

        MenuItem item5 = new MenuItem(400,"Fries","Entree", 5.35, "https://media.istockphoto.com/photos/fast-food-picture-id531189325?b=1&k=20&m=531189325&s=170667a&w=0&h=AwqBLMunJMm4BZLGNB2VmeCaFsM0zU_U3HrUnUH3xRk=");
        MenuItem item6 = new MenuItem(800,"Bacon Burger","Side", 8.95, "https://media.istockphoto.com/photos/western-barbecue-bacon-cheeseburger-picture-id923270448?k=20&m=923270448&s=612x612&w=0&h=jkXjSmzPEw1rv7nMfJnvZCiCuuXWsOYAdoo0RQ9Wb60=");
        MenuItem item7 = new MenuItem(350,"Vanilla Shake","Drink", 4.50, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXCK3wqAmpLRchnr8KCj1nUQcgnz-yh8A2Lg&usqp=CAU");
        MenuItem item8 = new MenuItem(180,"Sweet Tea","Dessert", 2.15, "https://media.istockphoto.com/photos/a-glass-of-frozen-lemon-black-tea-on-a-white-background-picture-id1155623658?k=20&m=1155623658&s=612x612&w=0&h=NENT_Yd2LhoeJJfHHRUUsVjIONkoIgOe8-R19eC8qxg=");

        MenuItem item9 = new MenuItem(400,"Fries","Dessert", 5.35, "https://media.istockphoto.com/photos/fast-food-picture-id531189325?b=1&k=20&m=531189325&s=170667a&w=0&h=AwqBLMunJMm4BZLGNB2VmeCaFsM0zU_U3HrUnUH3xRk=");
        MenuItem item10 = new MenuItem(800,"Bacon Burger","Dessert", 8.95, "https://media.istockphoto.com/photos/western-barbecue-bacon-cheeseburger-picture-id923270448?k=20&m=923270448&s=612x612&w=0&h=jkXjSmzPEw1rv7nMfJnvZCiCuuXWsOYAdoo0RQ9Wb60=");
        MenuItem item11 = new MenuItem(350,"Vanilla Shake","Entree", 4.50, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXCK3wqAmpLRchnr8KCj1nUQcgnz-yh8A2Lg&usqp=CAU");
        MenuItem item12 = new MenuItem(180,"Sweet Tea","Entree", 2.15, "https://media.istockphoto.com/photos/a-glass-of-frozen-lemon-black-tea-on-a-white-background-picture-id1155623658?k=20&m=1155623658&s=612x612&w=0&h=NENT_Yd2LhoeJJfHHRUUsVjIONkoIgOe8-R19eC8qxg=");

        menu.addItemMenu(item1);
        menu.addItemMenu(item2);
        menu.addItemMenu(item3);
        menu.addItemMenu(item4);
        menu.addItemMenu(item5);
        menu.addItemMenu(item6);
        menu.addItemMenu(item7);
        menu.addItemMenu(item8);
        menu.addItemMenu(item9);
        menu.addItemMenu(item10);
        menu.addItemMenu(item11);
        menu.addItemMenu(item12);


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
        /*tabScroll.setFitToHeight(false);
        tabScroll.setFitToWidth(false);
        hboxOuter.getChildren().addAll(spacing, menu.getItemShell(0), menu.getItemShell(1), menu.getItemShell(2), menu.getItemShell(3));
        */


        for(int i = 0; i < menu.getCount(); i++) {
            int index = i;

            ArrayList<Integer> ids = new ArrayList<Integer>();
            ArrayList<Integer> indices = new ArrayList<Integer>();
            menu.getItemImage(i).setPickOnBounds(true);
            menu.getItemImage(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    int counter = 0;
                    if(ids.size() == 0) {
                        order.addItemOrder(menu.getItem(index));
                        indices.add(counter++);
                        ids.add(menu.getItem(index).getId());
                    }
                    for(int j = 0; j < ids.size(); j++) {
                        if(ids.get(j) == menu.getItem(index).getId()) {
                            int k = order.getItem(indices.get(j)).getCount()+1;
                            order.getItem(indices.get(j)).setCount(k);
                        }
                        else {
                            order.addItemOrder(menu.getItem(index));
                            indices.add(index);
                            ids.add(menu.getItem(index).getId());
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

        Scene tableViewScene = new Scene(tableViewParent);

        OrderController controller = loader.getController();
        controller.orderConstruct(order);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

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
                System.out.println(types.size());
                vboxInner.getChildren().add(tabScrolls.get(i));
            }
        }
        scroll.setContent(null);
        scroll.setContent(vboxInner);
    }

    @FXML
    public void check() {
        vboxInner.getChildren().removeAll();
        scroll.setContent(null);
        System.out.println("?");
    }
}


