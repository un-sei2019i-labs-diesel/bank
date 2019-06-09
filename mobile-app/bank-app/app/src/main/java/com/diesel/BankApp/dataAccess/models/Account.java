package com.diesel.BankApp.dataAccess.models;


import com.j256.ormlite.field.DatabaseField;

public class Account {

    @DatabaseField
    int number;

    @DatabaseField
    int idLinked;

    @DatabaseField
    double balance;

    @DatabaseField
    String history;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getIdLinked() {
        return idLinked;
    }

    public void setIdLinked(int idLinked) {
        this.idLinked = idLinked;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Account() {

    }

    public Account(int number, int idLinked, double balance, String history) {
        this.number = number;
        this.idLinked = idLinked;
        this.balance = balance;
        this.history = history;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", idLinked='" + idLinked + '\'' +
                ", balance=" + balance +
                ", history='" + history + '\'' +
                '}';
    }
}
