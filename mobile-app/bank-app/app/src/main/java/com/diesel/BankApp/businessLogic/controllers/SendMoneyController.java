package com.diesel.BankApp.businessLogic.controllers;

import android.content.Context;
import android.util.Log;

import com.diesel.BankApp.dataAccess.database.Database;
import com.diesel.BankApp.dataAccess.models.Account;
import com.diesel.BankApp.dataAccess.repositories.AccountRepository;


import java.sql.SQLException;
import java.util.List;

public class SendMoneyController {
    AccountRepository accRepo = new AccountRepository();
    List<Account> result;
    Account cuentaEmisor;
    Account cuentaReceptor;
    double newBalance;

    public boolean sendMoney(int idEmisor, double cantidad, int idReceptor, Context context){
        //Busca la cuenta en la base de datos
        try {
            result = accRepo.getAccountByIdLinked(idEmisor,context);
            Log.d("demo account", result.toString());
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }

        //Compara el balance de la cuenta del emisor con la cantidad que se queire enviar
        cuentaEmisor = result.get(0);
        if(cuentaEmisor.getBalance()<cantidad){
            //TODO error
            return false;
        }else{
            //Resta la cantidad a enviar al balance del emisor
            newBalance = cuentaEmisor.getBalance()-cantidad;
            try {
                accRepo.updateAccountBalance(idEmisor,newBalance,context);
            }catch (SQLException e){
                e.printStackTrace();
            }


            //Obtiene la cuenta del receptor de la base de datos
            try {
                result = accRepo.getAccountByNumber(idReceptor,context);
                Log.d("demo account", result.toString());
            } catch (SQLException e){
                e.printStackTrace();
                return false;
            }
            cuentaReceptor = result.get(0);
            //Suma la cantidad a enviar al balance del receptor
            newBalance = cuentaReceptor.getBalance()+cantidad;
            try {
                accRepo.updateAccountBalance(idReceptor,newBalance,context);
            }catch (SQLException e){
                e.printStackTrace();
            }
            return true;

        }

    }
}
