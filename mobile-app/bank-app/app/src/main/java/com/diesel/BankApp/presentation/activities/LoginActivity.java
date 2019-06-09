package com.diesel.BankApp.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.diesel.BankApp.R;
import com.diesel.BankApp.businessLogic.controllers.LoginController;
import com.diesel.BankApp.businessLogic.controllers.MainController;
import com.diesel.BankApp.dataAccess.models.Account;
import com.diesel.BankApp.dataAccess.models.User;
import com.diesel.BankApp.dataAccess.repositories.AccountRepository;
import com.diesel.BankApp.dataAccess.repositories.UserRepository;

import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {

    //Declaration EditTexts
    EditText editTextUsername;
    EditText editTextPassword;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutUsername;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initCreateUserTextView();
        initCreateAccountTextView();
        initViews();
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
                    Account currentAccount = null;
                    int success = 0;
                    try {
                        //log in logic
                        LoginController controller = new LoginController();
                        success = controller.login(Username,Password,LoginActivity.this);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Check Authentication is successful or not
                    if (success != 0) {
                        UserRepository urepo = new UserRepository();
                        try {
                            currentUser = urepo.getUserByName(Username,LoginActivity.this).get(0);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        AccountRepository arepo = new AccountRepository();
                        try {
                            currentAccount = arepo.getAccountByIdLinked(currentUser.getId(),LoginActivity.this).get(0);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        MainController.cuenta = currentAccount;
                        MainController.usuario = currentUser;
                        Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();

                        //User Logged in Successfully Launch You home screen activity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        //User Logged in Failed
                        Snackbar.make(buttonLogin, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();

                    }
                }
            }
        });


    }


    //this method used to set Create account TextView text and click event( maltipal colors
    // for TextView yet not supported in Xmld so i have one it programmatically)
    private void initCreateUserTextView() {
        TextView textViewCreateUser = findViewById(R.id.textViewCreateUser);
        textViewCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, AccountRegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        textInputLayoutUsername = findViewById(R.id.textInputLayoutUsername);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

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