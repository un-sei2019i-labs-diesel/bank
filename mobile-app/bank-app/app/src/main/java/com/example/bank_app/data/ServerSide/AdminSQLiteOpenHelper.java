package com.example.bank_app.data.ServerSide;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, nombre, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //se crea la tabla de Usuario (id, fecha_cambio, nombre, clave, numero_cuenta)
        db.execSQL("create table Usuario(id numeric primary key, fecha_cambio numeric, nombre text, clave numeric, numero_cuenta numeric)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int version1, int version2) {

        db.execSQL("drop table if exists Usuario");

        db.execSQL("create table Usuario(id numeric primary key, fecha_cambio numeric, nombre text, clave numeric, numero_cuenta numeric)");

    }

}