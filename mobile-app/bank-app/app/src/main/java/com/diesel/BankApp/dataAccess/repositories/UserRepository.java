package com.diesel.BankApp.dataAccess.repositories;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.diesel.BankApp.dataAccess.database.Database;
import com.diesel.BankApp.dataAccess.models.User;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

public class UserRepository extends AppCompatActivity {

    Database dbHelper;

    public void create(User user, Context context){
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<User,Integer> UserDao = dbHelper.getUserRuntimeExceptionDao();

            //create
        UserDao.create(user);



        //query
        List<User> users = UserDao.queryForAll();
        Log.d("demo user", users.toString());

        OpenHelperManager.releaseHelper();
    }
}
