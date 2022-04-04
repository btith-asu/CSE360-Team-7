package com.example.cse360;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.*;


public class RestSideController implements Initializable {

    Menu list = new Menu();

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
    private TextField inputLink;

    @FXML
    private TextField inputTag;

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
        priceCol.setCellValueFactory(new PropertyValueFactory<>("priceString"));
        submit.setDisable(true);
        remove.setDisable(true);

        File file = new File("menuItems.txt");
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                MenuItem item1 = new MenuItem(1, 400,"Fries","Side", 5.35, "https://media.istockphoto.com/photos/fast-food-picture-id531189325?b=1&k=20&m=531189325&s=170667a&w=0&h=AwqBLMunJMm4BZLGNB2VmeCaFsM0zU_U3HrUnUH3xRk=", "French");
                MenuItem item2 = new MenuItem(2, 800,"Bacon Burger","Entree", 8.95, "https://media.istockphoto.com/photos/western-barbecue-bacon-cheeseburger-picture-id923270448?k=20&m=923270448&s=612x612&w=0&h=jkXjSmzPEw1rv7nMfJnvZCiCuuXWsOYAdoo0RQ9Wb60=", "Bacon");
                MenuItem item3 = new MenuItem(3, 350,"Vanilla Shake","Dessert", 4.50, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXCK3wqAmpLRchnr8KCj1nUQcgnz-yh8A2Lg&usqp=CAU", "Vanilla");
                MenuItem item4 = new MenuItem(4, 180,"Sweet Tea","Drink", 2.15, "https://media.istockphoto.com/photos/a-glass-of-frozen-lemon-black-tea-on-a-white-background-picture-id1155623658?k=20&m=1155623658&s=612x612&w=0&h=NENT_Yd2LhoeJJfHHRUUsVjIONkoIgOe8-R19eC8qxg=", "Sweet");

                list.addItemMenu(item1);
                list.addItemMenu(item2);
                list.addItemMenu(item3);
                list.addItemMenu(item4);


            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

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
        try {
            Image test = new Image(inputLink.getText());
            if(test.isError() == true) {
                disabled = true;
            }
        }
        catch (IllegalArgumentException e) {
            disabled = true;
        }
        submit.setDisable(disabled);
    }

    @FXML
    void deleteClicked() {
        list.removeItemOrder(menu.getSelectionModel().getSelectedItem());
        menu.getItems().removeAll(menu.getSelectionModel().getSelectedItem());
        updateEditView();
        if (menu.getItems().isEmpty()) {
            inputType.setText("");
            inputName.setText("");
            inputCal.setText("");
            inputPrice.setText("");
            inputLink.setText("");
            inputTag.setText("");
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
        inputLink.setText("");
        inputTag.setText("");
        remove.setDisable(true);
    }

    private void setupTable(){
        try {
            File file = new File("menuItems.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String data = scan.nextLine();
                ArrayList<String> parser = new ArrayList<>(Arrays.asList(data.split("\\\\")));

                MenuItem itemTemp = new MenuItem(Integer.parseInt(parser.get(0)), Integer.parseInt(parser.get(1)),parser.get(2),parser.get(3), Double.parseDouble(parser.get(4)), parser.get(5), parser.get(6));
                list.addItemMenu(itemTemp);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for(int i = 0; i < list.getCount(); i++) {
            menu.getItems().add(list.getItem(i));
        }
        submit.setDisable(true);
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
        inputLink.setText(clickedItem.getImageLink());
        inputTag.setText(clickedItem.getTag());
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
                item.setImageLink(inputLink.getText());
                item.setTag(inputTag.getText());

                menu.setItems(currentTableData);
                menu.refresh();

                try {
                    FileWriter fileWrite = new FileWriter("menuItems.txt");
                    for(int i =0; i< list.getCount(); i++) {
                        fileWrite.write(list.getItem(i).storeString());
                    }
                    fileWrite.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void addItem() {
        Random rand = new Random();
        boolean sameId = false;
        int newId = rand.nextInt(1000);

        for(int i = 0; i < list.getCount(); i++) {
            if(newId == list.getItem(i).getId()) {
                sameId = true;
                i = 0;
                newId = rand.nextInt(1000);
            }
        }


        MenuItem newItem = new MenuItem(newId, Integer.parseInt(inputCal.getText()),inputName.getText(),inputType.getText(), Double.parseDouble(inputPrice.getText()), inputLink.getText(), inputTag.getText());
        list.addItemMenu(newItem);
        menu.getItems().add(newItem);

        try {
            FileWriter fileWrite = new FileWriter("menuItems.txt");
            for(int i =0; i< list.getCount(); i++) {
                fileWrite.write(list.getItem(i).storeString());
            }
            fileWrite.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static boolean isNumeric(String str) {
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(str, pos);
        return str.length() == pos.getIndex();
    }
}
