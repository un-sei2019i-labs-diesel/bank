package com.diesel.BankApp.businessLogic.controllers;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.diesel.BankApp.dataAccess.models.User;
import com.diesel.BankApp.dataAccess.repositories.UserRepository;

import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class RegisterController {
    UserRepository userRepo = new UserRepository();
    List<User> result;
    //CLAVE
    private String clave = "Diesel";

    public boolean register(int ID, String username, String password, Context context){
        try {
            result = userRepo.getUserById(ID,context);
            Log.d("demo account", result.toString());
            //TODO la cuennta ya existe
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        userRepo.createUser(new User(ID ,username,String.valueOf(Calendar.getInstance().getTime().getTime()),password),context);
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

    public String desencriptar (String datos, String password) throws Exception {
        SecretKeySpec secretKey = generateKey(password);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] datosDecodificados = Base64.decode(datos, Base64.DEFAULT);
        byte[] datosDesencriptadosByte = cipher.doFinal(datosDecodificados);
        String datosDesencriptadosString = new String(datosDesencriptadosByte);
        return datosDesencriptadosString;
    }
}
