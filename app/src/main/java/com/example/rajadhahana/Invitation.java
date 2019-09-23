package com.example.rajadhahana;

public class Invitation {

    private String name;
    private String message;
    private int number;
    private String senderNumber;
    private String key;
    private String senderName;
    public static String mynumber;
    public static String myName;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public static String getMyName() {
        return myName;
    }

    public static void setMyName(String myName) {
        Invitation.myName = myName;
    }

    public String getSenderNumber() {
        return senderNumber;
    }

    public void setSenderNumber(String senderNumber) {
        this.senderNumber = senderNumber;
    }

    public static String getMynumber() {
        return mynumber;
    }

    public static void setMynumber(String mynumber) {
        Invitation.mynumber = mynumber;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Invitation() {
    }
}
