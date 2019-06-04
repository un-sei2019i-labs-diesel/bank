package com.diesel.BankApp.dataAccess.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//@DatabaseTable
public class User {

    @DatabaseField
     int id;

    @DatabaseField
     String userName;

    @DatabaseField
     String change_date;

    @DatabaseField
     String password;

    @DatabaseField
     String account_number;


    public User(){

    }
    public User(int id, String userName, String change_date, String password, String account_number) {
        this.id = id;
        this.userName = userName;
        this.change_date = change_date;
        this.password = password;
        this.account_number = account_number;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChange_date() {
        return change_date;
    }

    public void setChange_date(String change_date) {
        this.change_date = change_date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", change_date='" + change_date + '\'' +
                ", password='" + password + '\'' +
                ", account_number='" + account_number + '\'' +
                '}';
    }
}