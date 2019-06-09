package com.diesel.BankApp.dataAccess.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.util.Calendar;

import android.util.Base64;

import com.diesel.BankApp.R;
import com.diesel.BankApp.dataAccess.models.Account;
import com.diesel.BankApp.dataAccess.models.Administrator;
import com.diesel.BankApp.dataAccess.models.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Database extends OrmLiteSqliteOpenHelper {

    //CLAVE
    private String clave = "Diesel";

    private Dao<Account, Integer> accDao = null;
    private Dao<User,Integer> userDao = null;
    private Dao<Administrator,Integer> adminDao = null;
    private RuntimeExceptionDao<Account, Integer> accRuntimeDao = null;
    private RuntimeExceptionDao<User, Integer> userRuntimeDao = null;
    private RuntimeExceptionDao<Administrator, Integer> adminRuntimeDao = null;

    private static final String DATABASE_NAME = "BankAppDb.db";
    private static final int DATABASE_VERSION = 1;

    public Database(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);

    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Account.class);
            TableUtils.createTable(connectionSource,User.class);
            TableUtils.createTable(connectionSource,Administrator.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,Account.class,true);
            TableUtils.dropTable(connectionSource,User.class,true);
            TableUtils.dropTable(connectionSource,Administrator.class,true);
            onCreate(database, connectionSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Account, Integer> getAccDao() throws SQLException {

        if(accDao == null){
            accDao = getDao(Account.class);
        }

        return accDao;
    }
    public Dao<User, Integer> getUserDao() throws SQLException {

        if(userDao == null){
            userDao = getDao(User.class);
        }

        return userDao;
    }

    public Dao<Administrator, Integer> getAdminDao() throws SQLException {

        if(adminDao == null){
            adminDao = getDao(Administrator.class);
        }

        return adminDao;
    }


    public RuntimeExceptionDao<Account, Integer> getAccountRuntimeExceptionDao(){
        if(accRuntimeDao == null){
            accRuntimeDao = getRuntimeExceptionDao(Account.class);
        }
        return accRuntimeDao;
    }

    public RuntimeExceptionDao<User, Integer> getUserRuntimeExceptionDao(){
        if(userRuntimeDao == null){
            userRuntimeDao = getRuntimeExceptionDao(User.class);
        }
        return userRuntimeDao;
    }

    public RuntimeExceptionDao<Administrator, Integer> getAdminRuntimeExceptionDao(){
        if(adminRuntimeDao == null){
            adminRuntimeDao = getRuntimeExceptionDao(Administrator.class);
        }
        return adminRuntimeDao;
    }

/*
    //using this method we can add users to user table
    public void addUser(User user) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put id in  @values
        values.put(KEY_ID, user.id);

        //Put username in  @values
        values.put(KEY_USER_NAME, user.userName);

        //Put current date in  @values
        values.put(KEY_CHANGE_DATE, String.valueOf(Calendar.getInstance().getTime().getTime()));//valor en milisegundos desde January 1, 1970, 00:00:00 GMT

        //Put password in  @values
        values.put(KEY_PASSWORD, user.password);

        //Put null account_number in  @values
        values.put(KEY_ACCOUNT_NUMBER, "NULL");

        // insert row
        long todo_id = db.insert(TABLE_USERS, null, values);

        db.close();
    }

    public User Authenticate(User user) throws Exception {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " +  TABLE_USERS
                        + " WHERE " + KEY_USER_NAME + " = '" + user.userName + "'", null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {

            //if cursor has value then in user database there is user associated with this given username
            User user1 = new User(cursor.getString(cursor.getColumnIndex(KEY_ID)), cursor.getString(cursor.getColumnIndex(KEY_USER_NAME)),
                                cursor.getString(cursor.getColumnIndex(KEY_CHANGE_DATE)),cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)),cursor.getString(cursor.getColumnIndex(KEY_ACCOUNT_NUMBER)));

            cursor.close();
            db.close();
            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(desencriptar(user1.password, clave))){
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean doesExist(String ID, String Username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " +  TABLE_USERS
                        + " WHERE " + KEY_USER_NAME + " = '" + Username
                        + "' OR " + KEY_ID + " = " + ID, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given id or username so return true
            return true;
        }
        cursor.close();
        db.close();
        //if id and username does not exist return false
        return false;
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
    private SecretKeySpec generateKey(String password) throws Exception{
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] key = password.getBytes("UTF-8");
        key = sha.digest(key);
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        return secretKey;
    }*/
}