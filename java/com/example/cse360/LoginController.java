package com.example.cse360;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;



public class LoginController implements Initializable {
    private String userName = "test";
    private String password = "test";
    public boolean userVerified;
    public boolean passVerified;

    int index = 0;

    private ArrayList<User> users = new ArrayList<>();

    //Pane

    @FXML
    private HBox NavBar;

    @FXML
    private Region navCart;

    //Text
    @FXML
    private TextField usernameText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField passwordText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField numberText;

    @FXML
    private TextField dateText;

    @FXML
    private TextField cvvText;

    @FXML
    private Text currUser;

    @FXML
    private Text currEmail;

    @FXML
    private Text currPassword;

    //Buttons

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Button backButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button editButton;

    @FXML
    private Button orderButton;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            File file = new File("login.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String data = scan.nextLine();
                ArrayList<String> parser = new ArrayList<>(Arrays.asList(data.split("\\\\")));
                User tempUser = new User(Integer.parseInt(parser.get(0)), parser.get(1), parser.get(2), parser.get(3));
                if(parser.size() > 4) {
                    try {
                        PaymentCard newCard = new PaymentCard(parser.get(4), parser.get(5), parser.get(6), parser.get(7));
                        tempUser.setPaymentCard(newCard);
                    }
                    catch (ArrayIndexOutOfBoundsException e) {

                    }
                }
                users.add(tempUser);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        if(adminLogin(usernameText.getText(), passwordText.getText())) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("rest-order.fxml"));
            Parent tableViewParent = loader.load();

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            Scene tableViewScene = new Scene(tableViewParent, window.getWidth(), window.getHeight());

            window.setScene(tableViewScene);
            window.show();
        }
        else {
            for(int i = 0; i < users.size(); i++) {
                if (users.get(i).loginSuccessful(usernameText.getText(), passwordText.getText())) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("editAndMenu.fxml"));
                    Parent tableViewParent = loader.load();

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    Scene tableViewScene = new Scene(tableViewParent, window.getWidth(), window.getHeight());

                    users.get(i).setOrderCount(users.get(i).getOrderCount() + 1);
                    index = i;

                    window.setScene(tableViewScene);
                    window.show();
                }
            }
        }

    }

    public boolean adminLogin(String username, String password){
        if (username.equals("admin") && password.equals("password")) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    public void register(ActionEvent event) throws IOException {
        User tempUser = new User(usernameText.getText(), emailText.getText(), passwordText.getText());
        users.add(tempUser);
        users.get(users.size()-1).setOrderCount(users.get(users.size()-1).getOrderCount()+1);


        File file = new File("login.txt");
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                User oldUser = new User(2,"Brendan", "btith@asu.edu", "Tith");
                users.add(oldUser);

            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter fileWrite = new FileWriter("login.txt");
            for(int j = 0; j< users.size(); j++) {
                fileWrite.write(users.get(j).storeString());
            }
            fileWrite.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("editAndMenu.fxml"));
        Parent tableViewParent = loader.load();

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene tableViewScene = new Scene(tableViewParent, window.getWidth(), window.getHeight());

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("editAndMenu.fxml"));
        Parent tableViewParent = loader.load();

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene tableViewScene = new Scene(tableViewParent, window.getWidth(), window.getHeight());

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    public void update() {
        if(usernameText.getText().compareTo("") != 0) {
            users.get(index).setUsername(usernameText.getText());
        }
        if(emailText.getText().compareTo("") != 0) {
            users.get(index).setUsername(usernameText.getText());
        }
        if(passwordText.getText().compareTo("") != 0) {
            users.get(index).setUsername(usernameText.getText());
        }
        PaymentCard newCard = new PaymentCard(numberText.getText(), nameText.getText(), dateText.getText().trim(), cvvText.getText().trim());
        users.get(index).setPaymentCard(newCard);
    }

    @FXML
    public void edit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("edit.fxml"));
        Parent tableViewParent = loader.load();

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene tableViewScene = new Scene(tableViewParent, window.getWidth(), window.getHeight());

        window.setScene(tableViewScene);
        window.show();

        currUser.setText(users.get(index).getUsername());
        currEmail.setText(users.get(index).getEmail());
        currPassword.setText(users.get(index).getPassword());

        /*nameText.setText(users.get(index).getPaymentCard().getName());
        dateText.setText(users.get(index).getPaymentCard().getExpDate());
        numberText.setText(users.get(index).getPaymentCard().getNumber());
        cvvText.setText(String.valueOf(users.get(index).getPaymentCard().getCvv()));*/

    }

    @FXML
    public void order(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("menu.fxml"));
        Parent tableViewParent = loader.load();

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene tableViewScene = new Scene(tableViewParent, window.getWidth(), window.getHeight());

        window.setScene(tableViewScene);
        window.show();
    }

}
