package com.diesel.BankApp.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.diesel.BankApp.R;
import com.diesel.BankApp.dataAccess.database.Database;
import com.diesel.BankApp.dataAccess.models.Account;
import com.diesel.BankApp.dataAccess.models.Administrator;
import com.diesel.BankApp.dataAccess.models.User;
import com.diesel.BankApp.dataAccess.repositories.AccountRepository;
import com.diesel.BankApp.dataAccess.repositories.AdministratorRepository;
import com.diesel.BankApp.dataAccess.repositories.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    //Declaration EditTexts
    EditText editTextUsername;
    EditText editTextPassword;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutUsername;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonLogin;

    //Declaration Database
    Database database;

    AccountRepository accRepo = new AccountRepository();
    UserRepository userRepo = new UserRepository();
    AdministratorRepository adminRepo = new AdministratorRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database = new Database(this);
        initCreateAccountTextView();
        initViews();

        //<--------------------------------------INICIO DE METODOS ACCOUNT-------------------------------------------------------->
        /*create de la account*/
        accRepo.createAccount(new Account(1234567, "Account 2 text", 100000, "Account 2 Historia"),this);

        /*get account by number*/
        try {
            List<Account> result = accRepo.getAccountByNumber(1234567,this);
            Log.d("demo account", result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*get account bu Id Linked*/
        try {
            List<Account> result = accRepo.getAccountByIdLinked("Account 2 text", this);
            Log.d("demo account", result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*update Account number*/
        try {
            accRepo.updateAccountNumber(1234567,12345678,this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*update Account Linked Id*/
        try {
            accRepo.updateAccountdLinked(12345678, "Account 3 text", this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*update Account Balance*/
        try {
            accRepo.updateAccountBalance(12345678, 120000, this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*update Account Historial*/
        try {
            accRepo.updateAccountHistory(12345678, "Account 3 historia", this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*delte account*/
        try {
            accRepo.deleteAccount(12345678,this);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //<---------------------------------------INICIO DE METODOS USER-------------------------------------------------------->
        /*create del usuario*/
        userRepo.createUser(new User(123456,"tomperez","1234567","123456","987654123"),this);

        /*get user by Id*/
        try {
            List<User> result = userRepo.getUserById(123456,this);
            Log.d("demo user", result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*get user by name*/
        try {
            List<User> result = userRepo.getUserByName("tomperez", this);
            Log.d("demo user", result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*Upadate del id del usuario*/
        try {
            userRepo.updateUserID(123456,654321,this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*Change date del ususario*/
        try {
            userRepo.updateUserChangeDate(654321,this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*Cambiar el nombre de usuario del User*/
        try {
            userRepo.updateUserName(654321,"tpereza",this);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        /*Cambiar contraseña del usuario*/
        try {
            userRepo.updateUserPassword(654321, "012345", this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*Borrar usuario*/
        try {
            userRepo.deleteUser(654321,this);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //<-----------------------------------------------INICIO METODOS ADMINISTRATOR-------------------------------->
        /*create del administrator*/
        adminRepo.createAdministrator(new Administrator(567890, "098765"), this);

        /*gt administrator by id*/
        try {
            List <Administrator> result = adminRepo.getAdministratorById(567890, this);
            Log.d("demo admin", result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*update Administrator Id*/
        try {
            adminRepo.updateAdministratorId(567890, 123456,this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*update Administrator Password*/
        try {
            adminRepo.updateAdministratorPassword(123456, "123456",this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*delete administrator*/
        try {
            adminRepo.deleteAdministrator(123456,this);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //set click event of login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Check user input is correct or not
                if (validate()) {

                    //Get values from EditText fields
                    String Username = editTextUsername.getText().toString();
                    String Password = editTextPassword.getText().toString();


                    //Authenticate user
                    User currentUser = null;
                    try {
                        //currentUser = database.Authenticate(new User("null",Username,"null",Password,"null"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Check Authentication is successful or not
                    if (currentUser != null) {
                        Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();

                        //User Logged in Successfully Launch You home screen activity
                       /* Intent intent=new Intent(LoginActivity.this,HomeScreenActivity.class);
                        startActivity(intent);
                        finish();*/
                    } else {

                        //User Logged in Failed
                        Snackbar.make(buttonLogin, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();

                    }
                }
            }
        });


    }


    //this method used to set Create account TextView text and click event( maltipal colors
    // for TextView yet not supported in Xml so i have done it programmatically)
    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("</font><font color='#0c0099'>create account</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutUsername = (TextInputLayout) findViewById(R.id.textInputLayoutUsername);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

    }

    //This method is for handling fromHtml method deprecation
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String Username = editTextUsername.getText().toString();
        String Password = editTextPassword.getText().toString();

        //Handling validation for Username field
        if (Username.isEmpty()) {
            valid = false;
            textInputLayoutUsername.setError("Please enter valid username!");
            return valid;
        } else {
            if (Username.length() > 5) {
                valid = true;
                textInputLayoutUsername.setError(null);
            } else {
                valid = false;
                textInputLayoutUsername.setError("Username is too short!");
                return valid;
            }
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!");
            return valid;
        } else {
            if (Password.matches("\\d+")) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password must be numeric!");
                return valid;
            }

            if (Password.length() == 6) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password length must be 6!");
                return valid;
            }

        }

        return valid;
    }


}