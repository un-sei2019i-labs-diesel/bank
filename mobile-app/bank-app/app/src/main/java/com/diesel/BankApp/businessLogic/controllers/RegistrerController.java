package com.diesel.BankApp.businessLogic.controllers;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.diesel.BankApp.dataAccess.models.User;
import com.diesel.BankApp.dataAccess.repositories.UserRepository;

import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class RegistrerController {
    UserRepository userRepo = new UserRepository();
    List<User> result;

    public boolean registrer(int ID, String username, String password, Context context){
        try {
            result = userRepo.getUserById(ID,context);
            Log.d("demo account", result.toString());
            //TODO la cuennta ya existe
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        userRepo.createUser(new User(ID ,username,"123456",password),context);
        return true;
    }

    private String encriptar(String datos, String password) throws Exception{
        SecretKeySpec secretKey = generateKey(password);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        byte[] datosEncriptadosBytes = cipher.doFinal(datos.getBytes());
        String datosEncriptadosString = Base64.encodeToString(datosEncriptadosBytes, Base64.DEFAULT);
        return datosEncriptadosString;
    }
    private SecretKeySpec generateKey(String password) throws Exception{
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] key = password.getBytes("UTF-8");
        key = sha.digest(key);
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        return secretKey;
    }
}
