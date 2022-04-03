package com.example.checkout;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CheckoutController implements Initializable {
    private String address, city, name, coupon, zipcode;
    private PaymentCard card;
    protected double price;
    private boolean usedCoupon;
    private ArrayList<String> stateList;

    @FXML
    private TextField stateField;
    @FXML
    protected Button orderButton;
    @FXML
    private TextField zipField;
    @FXML
    private TextField couponField;
    @FXML
    private Text errorText;
    @FXML
    private TextField nameField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField numField;
    @FXML
    private TextField expField;
    @FXML
    private TextField cvvField;
    @FXML
    private Text orderText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usedCoupon = false;
        price = 0; // need to get the price from order total
        String s = "Alabama,Alaska,Arizona,Arkansas,California,Colorado,Connecticut,Delaware,Florida,Georgia,Hawaii,Idaho,Illinois,Indiana,Iowa,Kansas,Kentucky,Louisiana,Maine,Maryland,Massachusetts,Michigan,Minnesota,Mississippi,Missouri,Montana,Nebraska,Nevada,New Hampshire,New Jersey,New Mexico,New York,North Carolina,North Dakota,Ohio,Oklahoma,Oregon,Pennsylvania,Rhode Island,South Carolina,South Dakota,Tennessee,Texas,Utah,Vermont,Virginia,Washington,West Virginia,Wisconsin,Wyoming";
        stateList = new ArrayList<String>();
        while (s.length() > 8) {
            int index = s.indexOf(',');
            String s2 = s.substring(0, index);
            s = s.substring(index+1);
            stateList.add(s2.toLowerCase());
        }
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        orderText.setText("ORDER TOTAL: $" + df.format(price));
        stateList.add(s);
    }

    @FXML
    public void keyPressed(KeyEvent keyEvent) {
        boolean disabled = (nameField.getText().trim().isEmpty() || cityField.getText().trim().isEmpty() || addressField.getText().trim().isEmpty() || numField.getText().trim().isEmpty() || expField.getText().trim().isEmpty() || cvvField.getText().trim().isEmpty());
        calculateTotal();
        orderButton.setDisable(disabled);
    }

    @FXML
    public void placeOrder() {
        this.name = nameField.getText().trim();
        this.address = addressField.getText().trim();
        this.city = cityField.getText().trim();
        this.zipcode = zipField.getText().trim();
        this.card = new PaymentCard(numField.getText(), this.name, expField.getText().trim(), cvvField.getText().trim());
        boolean placed = inputInfo();
        if (placed) {
            errorText.setFill(Color.GREEN);
            errorText.setText("Order has been placed!");
        }
    }

    public boolean inputInfo() {
        errorText.setText("");
        errorText.setFill(Color.RED);
        if (stateList.contains(stateField.getText().toLowerCase().trim())) {
            if (card.verifyCard() == true) {
                if (zipcode.length() == 5) {
                    return true;
                } else {
                    errorText.setText("Invalid ZIP");
                }
            } else {
                errorText.setText("Unable to Verify Card");
            }
        } else {
            errorText.setText("Invalid State");
        }
        return false;
    }

    public void calculateTotal() {
        if (couponField.getText().toUpperCase().trim().equals("BURGER TIME") || couponField.getText().toUpperCase().trim().equals("FREE5")) {
            if (usedCoupon == false) {
                usedCoupon = true;
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                df.setMinimumFractionDigits(2);
                orderText.setText("ORDER TOTAL: $" + df.format(price) + " - $5.00 = $" + df.format(price-5));
                price -= 5;
            }
        }
    }


}
