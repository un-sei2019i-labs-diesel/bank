package com.diesel.BankApp.dataAccess.models;

import com.j256.ormlite.field.DatabaseField;

public class Administrator {

    @DatabaseField
    int id;

    @DatabaseField
    String password;

    public Administrator() {
        
    }

    public Administrator(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", password='" + password + '\'' +
                '}';
    }
}
