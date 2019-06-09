package com.diesel.BankApp.presentation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.diesel.BankApp.R;
import com.diesel.BankApp.businessLogic.controllers.RegisterController;


public class RegisterActivity extends AppCompatActivity {

    //Declaration EditTexts
    EditText editTextID;
    EditText editTextUsername;
    EditText editTextPassword;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutID;
    TextInputLayout textInputLayoutUsername;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonRegister;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initTextViewLogin();
        initViews();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    int ID = Integer.parseInt(editTextID.getText().toString());
                    String Username = editTextUsername.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    //Register logic
                    RegisterController controller = new RegisterController();
                    boolean success = controller.register(ID,Username,Password, RegisterActivity.this);
                    if (success == true){
                        Snackbar.make(buttonRegister, "User created successfully! Please Log in ", Snackbar.LENGTH_LONG).show();
                    }else{
                        Snackbar.make(buttonRegister, "User creation failed! Please try again ", Snackbar.LENGTH_LONG).show();
                    }

                }
            }
        });
    }


    //this method used to set Login TextView click event
    private void initTextViewLogin() {
        TextView textViewLogin = findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextID = findViewById(R.id.editTextID);
        textInputLayoutUsername = findViewById(R.id.textInputLayoutUsername);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        textInputLayoutID = findViewById(R.id.textInputLayoutID);
        buttonRegister = findViewById(R.id.buttonRegister);

    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String ID = editTextID.getText().toString();
        String Username = editTextUsername.getText().toString();
        String Password = editTextPassword.getText().toString();

        //Handling validation for id field
        if (ID.isEmpty()) {
            valid = false;
            textInputLayoutID.setError("Please enter valid id!");
            return valid;
        } else {
            if (ID.matches("\\d+")) {
                valid = true;
                textInputLayoutID.setError(null);
            } else {
                valid = false;
                textInputLayoutID.setError("ID must be numeric!");
                return valid;
            }

            if (ID.length() > 5) {
                valid = true;
                textInputLayoutID.setError(null);
            } else {
                valid = false;
                textInputLayoutID.setError("Id is too short!");
                return valid;
            }

        }

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