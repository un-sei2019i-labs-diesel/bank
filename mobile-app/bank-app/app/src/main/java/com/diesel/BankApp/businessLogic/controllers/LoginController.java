package com.diesel.BankApp.businessLogic.controllers;

import android.content.Context;

import com.diesel.BankApp.dataAccess.models.User;
import com.diesel.BankApp.dataAccess.repositories.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class LoginController extends MainController {
    UserRepository userRepo = new UserRepository();
    List<User> result;
    User currentUser;
    int userID;


    public int login(String username, String password, Context context){
        //Busca si la cuenta existe en la base de datos
        try {
            result = userRepo.getUserByName(username,context);
        }catch (SQLException e){
            e.printStackTrace();
            //TODO error la cuenta no existe
            return 0;
        }
        currentUser = result.get(0);
        //verifica que la contraseña sea coerrecta.
        if(currentUser.getPassword()!=password){
            //TODO error contraseña incorrecta
            return 0;
        }else{
            userID = currentUser.getId();
            return userID;
            //TODO exito
        }
    }

}
