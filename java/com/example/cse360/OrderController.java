package com.example.cse360;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    DecimalFormat df = new DecimalFormat("0.00");

    private Order order = new Order();

    @FXML
    private TableView<MenuItem> orderedItems;

    // Columns:
    @FXML
    private TableColumn<MenuItem, String> numCol;

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

    @FXML
    private Button payButton;

    @FXML
    private Button menuButton;

    @FXML
    private Text priceText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numCol.setCellValueFactory(new PropertyValueFactory<>("count"));
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
                                MenuItem clickedItem = getTableView().getItems().get(getIndex());
                                if (clickedItem == null) {
                                    return;
                                }
                                int k = clickedItem.getCount();
                                clickedItem.setCount(k+1);
                                //ObservableList<MenuItem> currentTableData = orderedItems.getItems();
                                //orderedItems.setItems(currentTableData);
                                orderedItems.refresh();
                                priceText.setText("$" + df.format(order.totalPrice()));

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
                                MenuItem clickedItem = getTableView().getItems().get(getIndex());
                                if (clickedItem == null) {
                                    return;
                                }
                                int k = clickedItem.getCount();
                                if(k > 0) {
                                    clickedItem.setCount(k-1);
                                }
                                //ObservableList<MenuItem> currentTableData = orderedItems.getItems();
                                //orderedItems.setItems(currentTableData);
                                orderedItems.refresh();
                                priceText.setText("$" + df.format(order.totalPrice()));
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
        System.out.println(orderedItems);
    }

    private void setupTable(){

        //orderedItems.getItems().addAll(item1,item2,item3,item4);
    }

    public void orderConstruct(Order incomeOrder) {
        orderedItems.getItems().clear();
        order = incomeOrder;
        //System.out.println(order.getCount());
        for(int i = 0; i < order.getCount(); i++) {
            orderedItems.getItems().add(order.getItem(i));
        }
        priceText.setText("$" + df.format(order.totalPrice()));
        //System.out.println(order.getCount());
    }

    @FXML
    public void backMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("menu.fxml"));
        Parent tableViewParent = loader.load();

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene tableViewScene = new Scene(tableViewParent, window.getWidth(), window.getHeight());

        MenuController controller = loader.getController();
        controller.orderConstruct(order);

        //window.setHeight(window.getHeight());
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    public void payMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("checkout.fxml"));
        Parent tableViewParent = loader.load();

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene tableViewScene = new Scene(tableViewParent, window.getWidth(), window.getHeight());

        CheckoutController controller = loader.getController();
        controller.payConstruct(order.totalPrice(), order);

        window.setScene(tableViewScene);
        window.show();
    }


}
