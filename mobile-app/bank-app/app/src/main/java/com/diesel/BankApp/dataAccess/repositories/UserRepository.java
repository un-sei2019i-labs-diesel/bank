package com.diesel.BankApp.dataAccess.repositories;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.diesel.BankApp.dataAccess.database.Database;
import com.diesel.BankApp.dataAccess.models.User;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class UserRepository extends AppCompatActivity {

    Database dbHelper;


    public void createUser(User user, Context context) {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);
        RuntimeExceptionDao<User, Integer> UserDao = dbHelper.getUserRuntimeExceptionDao();
        //create
        UserDao.create(user);

        //query
        List<User> users = UserDao.queryForAll();
        Log.d("demo user", users.toString());

        OpenHelperManager.releaseHelper();

    }

    public List<User> getUserById(int Id, Context context)throws SQLException {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<User, Integer> UserDao = dbHelper.getUserRuntimeExceptionDao();

        // get our query builder from the DAO
        QueryBuilder<User, Integer> queryBuilder =
                UserDao.queryBuilder();
        // the 'id' field must be equal to Id
        queryBuilder.where().eq("id", Id);
        // prepare our sql statement
        PreparedQuery<User> preparedQuery = queryBuilder.prepare();


        List<User> userList = UserDao.query(preparedQuery);
        Log.d("demo user", userList.toString());

        OpenHelperManager.releaseHelper();
        return userList;
    }

    public List<User> getUserByName(String name, Context context)throws SQLException {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<User, Integer> UserDao = dbHelper.getUserRuntimeExceptionDao();

        // get our query builder from the DAO
        QueryBuilder<User, Integer> queryBuilder =
                UserDao.queryBuilder();
        // the 'id' field must be equal to Id
        queryBuilder.where().eq("name", name);
        // prepare our sql statement
        PreparedQuery<User> preparedQuery = queryBuilder.prepare();


        List<User> userList = UserDao.query(preparedQuery);
        Log.d("demo user", userList.toString());

        OpenHelperManager.releaseHelper();
        return userList;
    }

    public void updateUserID(int oldId, int newId, Context context) throws SQLException {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<User, Integer> UserDao = dbHelper.getUserRuntimeExceptionDao();
        UpdateBuilder<User, Integer> updateBuilder = UserDao.updateBuilder();


        // set the criteria like you would a QueryBuilder
        updateBuilder.where().eq("id", oldId);
        // update the value of your field(s)
        updateBuilder.updateColumnValue("id", newId /* value */);
        updateBuilder.update();

        List<User> users = UserDao.queryForAll();
        Log.d("demo user", users.toString());

        OpenHelperManager.releaseHelper();
    }

    public void updateUserChangeDate(int Id, Context context) throws SQLException {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<User, Integer> UserDao = dbHelper.getUserRuntimeExceptionDao();
        UpdateBuilder<User, Integer> updateBuilder = UserDao.updateBuilder();


        // set the criteria like you would a QueryBuilder
        updateBuilder.where().eq("id", Id);
        // update the value of your field(s)
        updateBuilder.updateColumnValue("changeDate", String.valueOf(Calendar.getInstance().getTime().getTime() /* value */));
        updateBuilder.update();

        List<User> users = UserDao.queryForAll();
        Log.d("demo user", users.toString());

        OpenHelperManager.releaseHelper();
    }

    public void updateUserName(int Id, String newName, Context context) throws SQLException {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<User, Integer> UserDao = dbHelper.getUserRuntimeExceptionDao();
        UpdateBuilder<User, Integer> updateBuilder = UserDao.updateBuilder();


        // set the criteria like you would a QueryBuilder
        updateBuilder.where().eq("id", Id);
        // update the value of your field(s)
        updateBuilder.updateColumnValue("name", newName /* value */);
        updateBuilder.update();

        List<User> users = UserDao.queryForAll();
        Log.d("demo user", users.toString());

        OpenHelperManager.releaseHelper();
    }

    public void updateUserPassword(int Id, String newPassword, Context context) throws SQLException {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<User, Integer> UserDao = dbHelper.getUserRuntimeExceptionDao();
        UpdateBuilder<User, Integer> updateBuilder = UserDao.updateBuilder();


        // set the criteria like you would a QueryBuilder
        updateBuilder.where().eq("id", Id);
        // update the value of your field(s)
        updateBuilder.updateColumnValue("password", newPassword /* value */);
        updateBuilder.update();

        List<User> users = UserDao.queryForAll();
        Log.d("demo user", users.toString());

        OpenHelperManager.releaseHelper();
    }

    public void deleteUser(int Id, Context context) throws SQLException {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<User, Integer> UserDao = dbHelper.getUserRuntimeExceptionDao();
        DeleteBuilder<User, Integer> deleteBuilder =
                UserDao.deleteBuilder();

        // only delete the rows where id is Id
        deleteBuilder.where().eq("id", Id);
        deleteBuilder.delete();

        List<User> users = UserDao.queryForAll();
        Log.d("demo user", users.toString());

        OpenHelperManager.releaseHelper();

    }


}
