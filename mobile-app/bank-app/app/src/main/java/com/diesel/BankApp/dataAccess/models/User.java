package com.diesel.BankApp.dataAccess.models;

import com.j256.ormlite.field.DatabaseField;

//@DatabaseTable
public class User {

    @DatabaseField
     int id;

    @DatabaseField
     String name;

    @DatabaseField
     String changeDate;

    @DatabaseField
     String password;


    public User(){

    }
    public User(int id, String name, String changeDate, String password) {
        this.id = id;
        this.name = name;
        this.changeDate = changeDate;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", changeDate='" + changeDate + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}