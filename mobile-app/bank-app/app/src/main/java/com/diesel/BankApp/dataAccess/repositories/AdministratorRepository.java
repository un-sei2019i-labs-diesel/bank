package com.diesel.BankApp.dataAccess.repositories;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.diesel.BankApp.dataAccess.database.Database;
import com.diesel.BankApp.dataAccess.models.Administrator;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class AdministratorRepository extends AppCompatActivity {

    Database dbHelper;


    public void createAdministrator(Administrator admin, Context context) {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);
        RuntimeExceptionDao<Administrator, Integer> AdminDao = dbHelper.getAdminRuntimeExceptionDao();

        //create
        AdminDao.create(admin);

        //query
        List<Administrator> admins = AdminDao.queryForAll();
        Log.d("demo admin", admins.toString());

        OpenHelperManager.releaseHelper();

    }

    public List<Administrator> getAdministratorById(int Id, Context context) throws SQLException {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<Administrator, Integer> AdminDao = dbHelper.getAdminRuntimeExceptionDao();

        // get our query builder from the DAO
        QueryBuilder<Administrator, Integer> queryBuilder =
                AdminDao.queryBuilder();
        // the 'id' field must be equal to Id
        queryBuilder.where().eq("id", Id);
        // prepare our sql statement
        PreparedQuery<Administrator> preparedQuery = queryBuilder.prepare();


        List<Administrator> administratorList = AdminDao.query(preparedQuery);
        Log.d("demo admin", administratorList.toString());

        OpenHelperManager.releaseHelper();
        return administratorList;
    }

    public void updateAdministratorId(int oldId, int newId, Context context) throws SQLException {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<Administrator, Integer> AdminDao = dbHelper.getAdminRuntimeExceptionDao();
        UpdateBuilder<Administrator, Integer> updateBuilder = AdminDao.updateBuilder();


        // set the criteria like you would a QueryBuilder
        updateBuilder.where().eq("id", oldId);
        // update the value of your field(s)
        updateBuilder.updateColumnValue("id", newId /* value */);
        updateBuilder.update();

        List<Administrator> users = AdminDao.queryForAll();
        Log.d("demo admin", users.toString());

        OpenHelperManager.releaseHelper();
    }

    public void updateAdministratorPassword(int Id, String newPassword, Context context) throws SQLException {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<Administrator, Integer> AdminDao = dbHelper.getAdminRuntimeExceptionDao();
        UpdateBuilder<Administrator, Integer> updateBuilder = AdminDao.updateBuilder();


        // set the criteria like you would a QueryBuilder
        updateBuilder.where().eq("id", Id);
        // update the value of your field(s)
        updateBuilder.updateColumnValue("password", newPassword /* value */);
        updateBuilder.update();

        List<Administrator> users = AdminDao.queryForAll();
        Log.d("demo admin", users.toString());

        OpenHelperManager.releaseHelper();
    }

    public void deleteAdministrator(int Id, Context context) throws SQLException {
        dbHelper = OpenHelperManager.getHelper(context, Database.class);

        RuntimeExceptionDao<Administrator, Integer> AdminDao = dbHelper.getAdminRuntimeExceptionDao();
        DeleteBuilder<Administrator, Integer> deleteBuilder =
                AdminDao.deleteBuilder();

        // only delete the rows where id is Id
        deleteBuilder.where().eq("id", Id);
        deleteBuilder.delete();

        List<Administrator> users = AdminDao.queryForAll();
        Log.d("demo admin", users.toString());

        OpenHelperManager.releaseHelper();

    }

}
