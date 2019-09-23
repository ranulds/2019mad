package com.example.rajadhahana;

public class User {

    private static String myname;
    private static String mynumber;
    private String Name;
    private int ContactNo;
    private String Nic;
    private String Email;
    private String Password;
    private String Copassword;

    public User() {
    }

//    public static String getMyname() {
//        return myname;
//    }
//
//    public static void setMyname(String myname) {
//        User.myname = myname;
//    }
//
//    public static String getMynumber() {
//        return mynumber;
//    }
//
//    public static void setMynumber(String mynumber) {
//        User.mynumber = mynumber;
//    }

    public String getEmail() {
        return Email;
    }

    public String getCopassword() {
        return Copassword;
    }

    public void setCopassword(String copassword) {
        Copassword = copassword;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNic() {
        return Nic;
    }

    public void setNic(String nic) {
        Nic = nic;
    }

    public int getContactNo() {
        return ContactNo;
    }

    public void setContactNo(int contactNo) {
        ContactNo = contactNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

