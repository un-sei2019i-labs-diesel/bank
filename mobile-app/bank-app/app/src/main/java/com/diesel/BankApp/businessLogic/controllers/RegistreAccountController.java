package com.diesel.BankApp.businessLogic.controllers;

import android.content.Context;
import android.util.Log;

import com.diesel.BankApp.dataAccess.models.Account;
import com.diesel.BankApp.dataAccess.models.User;
import com.diesel.BankApp.dataAccess.repositories.AccountRepository;
import com.diesel.BankApp.dataAccess.repositories.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class RegistreAccountController {
    List<Account> result;
    List<User> resultUser;
    AccountRepository accRepo = new AccountRepository();
    UserRepository userRepo = new UserRepository();


    public boolean registrer(int numero, String linkedUsername, double balance, Context context){
        //verifica que exista la cuenta de usuario
        try {
            resultUser = userRepo.getUserByName(linkedUsername,context);
            Log.d("demo account", result.toString());
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        User usuario = resultUser.get(0);
        int idUsuario = usuario.getId();
        //verifica que no exista ya la cuenta de ahorros
        try {
            result = accRepo.getAccountByNumber(numero,context);
            Log.d("demo account", result.toString());
            //TODO la cuenta ya existe
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        //crea la nueva cuenta
        accRepo.createAccount(new Account(numero,idUsuario,balance, " "),context);
        return true;
    }
}
