package com.example.cse360;

public class User {

    private int orderCount = 0;
    private String username = "test";
    private String email = "test@gmail.com";
    private String password = "test";
    private PaymentCard paymentCard;

    public boolean userVerified;
    public boolean passVerified;


    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(int orderCount, String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.orderCount = orderCount;
    }

    public User(String username, String email, String password, PaymentCard paymentCard) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.paymentCard = paymentCard;
    }

    public boolean loginSuccessful(String username, String password){
        if (username.equals(this.username) && this.password.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String input) {
        this.username = input;
    }

    public void setEmail(String input) {
        this.email = input;
    }
    public void setPassword(String input) {
        this.password = input;
    }

    public PaymentCard getPaymentCard() {
        return this.paymentCard;
    }

    public void setPaymentCard(PaymentCard card) {
        paymentCard = card;
    }

    public void setOrderCount(int count) {
        this.orderCount = count;
    }

    public int getOrderCount() {
        return this.orderCount;
    }

    public String storeString() {
        if(paymentCard == null) {
            return orderCount + "\\" + username + "\\" + email + "\\" +
                    password + "\n";
        }
        else {
            return orderCount + "\\" + username + "\\" + email + "\\" +
                    password + "\\" + paymentCard.storeString() +"\n";
        }
    }
}
