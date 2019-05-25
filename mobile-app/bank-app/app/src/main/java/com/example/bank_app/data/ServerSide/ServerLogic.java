package com.example.bank_app.data.ServerSide;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import java.util.Calendar;



public class ServerLogic extends Activity {


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    // Registro nuevo usuario
    public void bdregistro(int rid,String rnombre,int rclave) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,

                "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();
        //id  fecha_cambio nombre  clave  numero_cuenta
        String id = String.valueOf(rid);
        String fecha_cambio = String.valueOf(Calendar.getInstance().getTime().getTime()); //valor en milisegundos desde January 1, 1970, 00:00:00 GMT
        String nombre = rnombre;
        String clave = String.valueOf(rclave);//sin encriptar
        String numero_cuenta = "null";

        ContentValues registro = new ContentValues();

        registro.put("id", id);
        registro.put("fecha_cambio", fecha_cambio);
        registro.put("nombre", nombre);
        registro.put("clave", clave);
        registro.put("numero_cuenta", numero_cuenta);


        // los inserto en la base de datos
        bd.insert("Usuario", null, registro);

        bd.close();


    }

    // Log in usuario por nombre y clave
    public void bdlogin(String lnombre,int lclave) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,

                "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery(

                "select * from Usuario where nombre=" + lnombre + "and clave=" + String.valueOf(lclave), null);

        if (fila.moveToFirst()) {

            //Log in exitoso

        } else

            //Log in fallido

        bd.close();

    }


    /* Método para dar de baja al usuario insertado*/
    /*public void baja(View v) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,

                "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String dni = et1.getText().toString();

        // aquí borro la base de datos del usuario por el dni
        int cant = bd.delete("usuario", "dni=" + dni, null);

        bd.close();

        et1.setText(""); et2.setText(""); et3.setText(""); et4.setText("");

        if (cant == 1)

            Toast.makeText(this, "Usuario eliminado",

                    Toast.LENGTH_SHORT).show();

        else

            Toast.makeText(this, "No existe usuario",

                    Toast.LENGTH_SHORT).show();
    }*/


    // Método para modificar la información del usuario
    /*public void modificacion(View v) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,

                "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String dni = et1.getText().toString();
        String nombre = et2.getText().toString();
        String ciudad = et3.getText().toString();
        String numero = et4.getText().toString();

        ContentValues registro = new ContentValues();

        // actualizamos con los nuevos datos, la información cambiada
        registro.put("nombre", nombre);
        registro.put("ciudad", ciudad);
        registro.put("numero", numero);

        int cant = bd.update("usuario", registro, "dni=" + dni, null);

        bd.close();

        if (cant == 1)

            Toast.makeText(this, "Datos modificados con éxito", Toast.LENGTH_SHORT)

                    .show();

        else

            Toast.makeText(this, "No existe usuario",

                    Toast.LENGTH_SHORT).show();

    }*/

}