package com.diesel.BankApp.businessLogic.controllers;

import android.content.Context;

import com.diesel.BankApp.dataAccess.models.Account;
import com.diesel.BankApp.dataAccess.repositories.AccountRepository;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TransactionController {
    AccountRepository accRepo = new AccountRepository();
    List<Account> result;
    Account cuentaEmisor;
    Account cuentaReceptor;
    double newBalance;

    public boolean sendMoney(int numeroEmisor, double cantidad, int numeroReceptor, Context context) {
        //Busca la cuenta en la base de datos
        try {
            result = accRepo.getAccountByNumber(numeroEmisor, context);
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }

        //Compara el balance de la cuenta del emisor con la cantidad que se quiere enviar
        if (!(result.isEmpty())) {
            cuentaEmisor = result.get(0);
        } else {
            return false;
        }

        if(cuentaEmisor.getBalance()<cantidad){
            //TODO error
            return false;
        }else{
            //Obtiene la cuenta del receptor de la base de datos
            try {
                result = accRepo.getAccountByNumber(numeroReceptor, context);
            } catch (SQLException e){
                e.printStackTrace();
                return false;
            }

            if (!(result.isEmpty())) {
                cuentaReceptor = result.get(0);
            } else {
                return false;
            }

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            //Resta la cantidad a enviar al balance del emisor
            newBalance = cuentaEmisor.getBalance()-cantidad;
            try {
                accRepo.updateAccountBalance(numeroEmisor, newBalance, context);
                String hist = cuentaEmisor.getHistory();
                String newTran = dateFormat.format(date) + " | " + cuentaReceptor.getNumber() + " | -" + cantidad + ";";
                accRepo.updateAccountHistory(numeroEmisor, newTran + hist, context);
            }catch (SQLException e){
                e.printStackTrace();
            }

            //Suma la cantidad a enviar al balance del receptor
            newBalance = cuentaReceptor.getBalance()+cantidad;
            try {
                accRepo.updateAccountBalance(numeroReceptor, newBalance, context);
                String hist = cuentaReceptor.getHistory();
                String newTran = dateFormat.format(date) + " | " + cuentaEmisor.getNumber() + " | +" + cantidad + ";";
                accRepo.updateAccountHistory(numeroReceptor, newTran + hist, context);
            }catch (SQLException e){
                e.printStackTrace();
            }
            return true;

        }

    }
}
