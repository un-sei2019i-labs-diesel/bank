package com.diesel.BankApp.dataAccess.repositories;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.diesel.BankApp.dataAccess.database.Database;
import com.diesel.BankApp.dataAccess.models.Account;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

public class AccountRepository extends AppCompatActivity {

    Database dbHelper;

    public void create(Account account,Context context){
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<Account, Integer> noteDao =  dbHelper.getNoteRuntimeExceptionDao();

        //create
        noteDao.create(account);

        //query
        List<Account> acc = noteDao.queryForAll();
        Log.d("demo", acc.toString());

        OpenHelperManager.releaseHelper();
    }
}
