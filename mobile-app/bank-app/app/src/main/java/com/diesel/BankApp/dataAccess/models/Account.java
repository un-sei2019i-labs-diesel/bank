package com.diesel.BankApp.dataAccess.models;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

public class Account {

    @DatabaseField
    int numero;

    @DatabaseField
    String id_asociado;

    @DatabaseField
    double balance;

    @DatabaseField
    String historial;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getId_asociado() {
        return id_asociado;
    }

    public void setId_asociado(String id_asociado) {
        this.id_asociado = id_asociado;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public Account() {

    }

    public Account(int numero, String id_asociado, double balance, String historial) {
        this.numero = numero;
        this.id_asociado = id_asociado;
        this.balance = balance;
        this.historial = historial;
    }

    @Override
    public String toString() {
        return "Account{" +
                "numero=" + numero +
                ", id_asociado='" + id_asociado + '\'' +
                ", balance=" + balance +
                ", historial='" + historial + '\'' +
                '}';
    }
}
