package com.example.cse360;

public class PaymentCard {
    private String number, name, expDate;
    private int cvv;

    PaymentCard() {
        this.number = "";
        this.name = "";
        this.expDate = "";
        this.cvv = 1;
    }

    PaymentCard(String number, String name, String expDate, String cvv2) {
        this.number = number.replaceAll(" ","");
        this.name = name;
        this.expDate = expDate;
        try {
            this.cvv = Integer.parseInt(cvv2);
        } catch (NumberFormatException nfe) {
            cvv = 1;
        }
    }

    public boolean verifyCard() {
        if (number.length() == 16) {
            if (expDate.contains("/") && expDate.length() > 3 && expDate.length() < 6) {
                if (cvv > 99 && cvv < 1000) {
                    return true;
                }
            }
        }
        return false;
    }

    public String storeString() {
        return number + "\\" + name + "\\" + expDate + "\\" + cvv;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getCvv() {
        return cvv;
    }
}
