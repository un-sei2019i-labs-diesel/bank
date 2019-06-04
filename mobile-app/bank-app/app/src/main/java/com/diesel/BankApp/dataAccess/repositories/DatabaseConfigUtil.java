package com.diesel.BankApp.dataAccess.repositories;
import com.diesel.BankApp.dataAccess.models.Account;
import com.diesel.BankApp.dataAccess.models.User;

import java.io.IOException;
import java.sql.SQLException;

import static com.j256.ormlite.android.apptools.OrmLiteConfigUtil.writeConfigFile;

public class DatabaseConfigUtil extends Object {

    private static final Class<?>[] classes = new Class[]{Account.class, User.class};

    public static void main(String [] args) throws IOException, SQLException {
        writeConfigFile("ormlite_config.txt", classes);

    }
}

