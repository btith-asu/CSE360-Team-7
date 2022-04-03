package com.example.cse360;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.cse360.RestSide.isNumeric;

public class RestSideController implements Initializable {

    @FXML
    private TableView<MenuItem> menu;

    // Columns:

    @FXML
    private TableColumn<MenuItem, Integer> idCol;

    @FXML
    private TableColumn<MenuItem, String> typeCol;

    @FXML
    private TableColumn<MenuItem, String> nameCol;

    @FXML
    private TableColumn<MenuItem, Integer> calCol;

    @FXML
    private TableColumn<MenuItem, String> priceCol;

    // TextFields:

    private int inputId;

    @FXML
    private Text modeText;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputType;

    @FXML
    private TextField inputCal;

    @FXML
    private TextField inputPrice;

    @FXML
    private Button submit;

    @FXML
    private Button add;

    @FXML
    private Button remove;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        calCol.setCellValueFactory(new PropertyValueFactory<>("calories"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        submit.setDisable(true);
        remove.setDisable(true);
        setupTable();
    }
    @FXML
    void submit() {
        if (Objects.equals(modeText.getText(), "Edit Item")) {
            updateItem();
        } else {
            addItem();
        }
    }

    @FXML
    void rowClicked() {
        updateEditView();
    }

    @FXML
    void keyPressed() {
        boolean disabled = (inputName.getText().trim().isEmpty() || inputType.getText().trim().isEmpty() || inputCal.getText().trim().isEmpty() || inputPrice.getText().trim().isEmpty()
                || !isNumeric(inputCal.getText()) || !isNumeric(inputPrice.getText()));
        submit.setDisable(disabled);
    }

    @FXML
    void deleteClicked() {
        menu.getItems().removeAll(menu.getSelectionModel().getSelectedItem());
        updateEditView();
        if (menu.getItems().isEmpty()) {
            inputType.setText("");
            inputName.setText("");
            inputCal.setText("");
            inputPrice.setText("");
            modeText.setText("Add Item");
            submit.setText("Add");
            add.setVisible(false);
            remove.setDisable(true);
            submit.setDisable(true);
        }
    }

    @FXML
    void addClicked() {
        menu.getSelectionModel().clearSelection();
        add.setVisible(false);
        modeText.setText("Add Item");
        submit.setText("Add");
        submit.setDisable(true);
        inputType.setText("");
        inputName.setText("");
        inputCal.setText("");
        inputPrice.setText("");
        remove.setDisable(true);
    }

    private void setupTable(){
        MenuItem item1 = new MenuItem(400,"Fries","Side", "$5.35");
        MenuItem item2 = new MenuItem(800,"Bacon Burger","Entree", "$8.95");
        MenuItem item3 = new MenuItem(350,"Vanilla Shake","Dessert", "$4.50");
        MenuItem item4 = new MenuItem(180,"Sweat Tea","Drink", "$2.15");
        menu.getItems().addAll(item1,item2,item3,item4);
    }

    private void updateEditView() {
        MenuItem clickedItem = menu.getSelectionModel().getSelectedItem();
        if (clickedItem == null) {
            return;
        }
        modeText.setText("Edit Item");
        submit.setText("Save");
        add.setVisible(true);
        inputId = clickedItem.getId();
        inputType.setText(String.valueOf(clickedItem.getType()));
        inputName.setText(String.valueOf(clickedItem.getName()));
        inputCal.setText(String.valueOf(clickedItem.getCalories()));
        inputPrice.setText(clickedItem.getPriceString().replace("$", ""));
        submit.setDisable(false);
        remove.setDisable(false);
    }

    private void updateItem() {
        ObservableList<MenuItem> currentTableData = menu.getItems();
        int currentMenuId = inputId;

        for (MenuItem item : currentTableData) {
            if(item.getId() == currentMenuId) {
                item.setType(inputType.getText());
                item.setName(inputName.getText());
                DecimalFormat df = new DecimalFormat("0.00");
                item.setPriceString("$" + df.format(Double.parseDouble(inputPrice.getText())));
                item.setCalories(Integer.parseInt(inputCal.getText()));

                menu.setItems(currentTableData);
                menu.refresh();
                break;
            }
        }
    }

    private void addItem() {
        DecimalFormat df = new DecimalFormat("0.00");
        MenuItem newItem = new MenuItem(Integer.parseInt(inputCal.getText()),inputName.getText(),inputType.getText(), ("$" + df.format(Double.parseDouble(inputPrice.getText()))));
        menu.getItems().add(newItem);
    }
}