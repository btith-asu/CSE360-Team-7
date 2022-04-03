package com.example.cse360;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    private Order order;

    @FXML
    private TableView<MenuItem> orderedItems;

    // Columns:

    @FXML
    private TableColumn<MenuItem, String> itemCol;

    @FXML
    private TableColumn<MenuItem, Integer> calCol;

    @FXML
    private TableColumn<MenuItem, String> priceCol;

    @FXML
    private TableColumn<MenuItem, String> editCol;

    @FXML
    private TableColumn<MenuItem, String> addCol;

    @FXML
    private TableColumn<MenuItem, String> removeCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        calCol.setCellValueFactory(new PropertyValueFactory<>("calories"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("priceString"));
        //addCol.setCellValueFactory(new PropertyValueFactory<>("Add"));
        //removeCol.setCellValueFactory(new PropertyValueFactory<>("Remove"));

        Callback<TableColumn<MenuItem, String>, TableCell<MenuItem, String>> cellFactory = new Callback<TableColumn<MenuItem, String>, TableCell<MenuItem, String>>() {
            @Override
            public TableCell call(final TableColumn<MenuItem, String> param) {
                TableCell<MenuItem, String> cell = new TableCell<MenuItem, String>() {
                    Button add = new Button("Add");
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            add.setOnAction(event -> {
                                System.out.println("working?");
                            });
                            setGraphic(add);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        addCol.setCellFactory(cellFactory);

        Callback<TableColumn<MenuItem, String>, TableCell<MenuItem, String>> cellFactory2 = new Callback<TableColumn<MenuItem, String>, TableCell<MenuItem, String>>() {
            @Override
            public TableCell call(final TableColumn<MenuItem, String> param) {
                TableCell<MenuItem, String> cell = new TableCell<MenuItem, String>() {
                    Button remove = new Button("Remove");
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            remove.setOnAction(event -> {
                                System.out.println("working!");
                            });
                            setGraphic(remove);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        removeCol.setCellFactory(cellFactory2);
    }

    private void setupTable(){

        //orderedItems.getItems().addAll(item1,item2,item3,item4);
    }

    public void orderConstruct(Order incomeOrder) {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        order = incomeOrder;
        for(int i = 0; i < order.getCount(); i++) {
            orderedItems.getItems().add(order.getItem(i));
        }
    }
}
