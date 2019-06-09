package com.diesel.BankApp.dataAccess.repositories;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.diesel.BankApp.dataAccess.database.Database;
import com.diesel.BankApp.dataAccess.models.Account;
import com.diesel.BankApp.dataAccess.models.Administrator;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.*;

import java.sql.SQLException;
import java.util.List;

public class AccountRepository extends AppCompatActivity {

    Database dbHelper;

    public void createAccount(Account account,Context context){
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<Account, Integer> AccDao =  dbHelper.getAccountRuntimeExceptionDao();

        //create
        AccDao.create(account);

        //query
        List<Account> accountList = AccDao.queryForAll();
        Log.d("demo account", accountList.toString());

        OpenHelperManager.releaseHelper();
    }

    public List<Account> getAccountByNumber(int Id, Context context)throws SQLException {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<Account, Integer> AccDao = dbHelper.getAccountRuntimeExceptionDao();

        // get our query builder from the DAO
        QueryBuilder<Account, Integer> queryBuilder =
                AccDao.queryBuilder();
        // the 'id' field must be equal to Id
        queryBuilder.where().eq("number", Id);
        // prepare our sql statement
        PreparedQuery<Account> preparedQuery = queryBuilder.prepare();


        List<Account> accountList = AccDao.query(preparedQuery);
        Log.d("demo account", accountList.toString());

        OpenHelperManager.releaseHelper();
        return accountList;
    }

    public List<Account> getAccountByIdLinked(int idLinked, Context context)throws SQLException {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<Account, Integer> AccDao = dbHelper.getAccountRuntimeExceptionDao();

        // get our query builder from the DAO
        QueryBuilder<Account, Integer> queryBuilder =
                AccDao.queryBuilder();
        // the 'id' field must be equal to Id
        queryBuilder.where().eq("idLinked", idLinked);
        // prepare our sql statement
        PreparedQuery<Account> preparedQuery = queryBuilder.prepare();


        List<Account> accountList = AccDao.query(preparedQuery);
        Log.d("demo account", accountList.toString());

        OpenHelperManager.releaseHelper();
        return accountList;
    }

    public void updateAccountNumber(int oldNumber, int newNumber, Context context) throws SQLException {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<Account, Integer> AccDao = dbHelper.getAccountRuntimeExceptionDao();
        UpdateBuilder<Account, Integer> updateBuilder = AccDao.updateBuilder();


        // set the criteria like you would a QueryBuilder
        updateBuilder.where().eq("number", oldNumber);
        // update the value of your field(s)
        updateBuilder.updateColumnValue("number", newNumber /* value */);
        updateBuilder.update();

        List<Account> accountList  = AccDao.queryForAll();
        Log.d("demo account", accountList.toString());

        OpenHelperManager.releaseHelper();
    }

    public void updateAccountdLinked(int number, String newIdLinked, Context context) throws SQLException {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<Account, Integer> AccDao = dbHelper.getAccountRuntimeExceptionDao();
        UpdateBuilder<Account, Integer> updateBuilder = AccDao.updateBuilder();


        // set the criteria like you would a QueryBuilder
        updateBuilder.where().eq("number", number);
        // update the value of your field(s)
        updateBuilder.updateColumnValue("idLinked", newIdLinked /* value */);
        updateBuilder.update();

        List<Account> accountList  = AccDao.queryForAll();
        Log.d("demo account", accountList.toString());

        OpenHelperManager.releaseHelper();
    }

    public void updateAccountBalance(int number, double newBalance, Context context) throws SQLException {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<Account, Integer> AccDao = dbHelper.getAccountRuntimeExceptionDao();
        UpdateBuilder<Account, Integer> updateBuilder = AccDao.updateBuilder();


        // set the criteria like you would a QueryBuilder
        updateBuilder.where().eq("number", number);
        // update the value of your field(s)
        updateBuilder.updateColumnValue("balance", newBalance /* value */);
        updateBuilder.update();

        List<Account> accountList  = AccDao.queryForAll();
        Log.d("demo account", accountList.toString());

        OpenHelperManager.releaseHelper();
    }

    public void updateAccountHistory(int number, String newHistory, Context context) throws SQLException {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<Account, Integer> AccDao = dbHelper.getAccountRuntimeExceptionDao();
        UpdateBuilder<Account, Integer> updateBuilder = AccDao.updateBuilder();


        // set the criteria like you would a QueryBuilder
        updateBuilder.where().eq("number", number);
        // update the value of your field(s)
        updateBuilder.updateColumnValue("history", newHistory /* value */);
        updateBuilder.update();

        List<Account> accountList  = AccDao.queryForAll();
        Log.d("demo account", accountList.toString());

        OpenHelperManager.releaseHelper();
    }

    public void deleteAccount(int Id, Context context) throws SQLException {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<Account, Integer> AccDao = dbHelper.getAccountRuntimeExceptionDao();
        DeleteBuilder<Account, Integer> deleteBuilder =
                AccDao.deleteBuilder();

        // only delete the rows where id is Id
        deleteBuilder.where().eq("number", Id);
        deleteBuilder.delete();

        List<Account> accountList = AccDao.queryForAll();
        Log.d("demo account", accountList.toString());

        OpenHelperManager.releaseHelper();

    }
}
