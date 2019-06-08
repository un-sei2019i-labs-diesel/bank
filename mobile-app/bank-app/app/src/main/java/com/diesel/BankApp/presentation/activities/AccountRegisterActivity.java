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

public class AccountRegisterActivity extends AppCompatActivity {
    //Declaration EditTexts
    EditText editTextNumber;
    EditText editTextUsername;
    EditText editTextBalance;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutNumber;
    TextInputLayout textInputLayoutUsername;
    TextInputLayout textInputLayoutBalance;

    //Declaration Button
    Button buttonRegister;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountregister);
        initTextViewLogin();
        initViews();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    int number = Integer.parseInt(editTextNumber.getText().toString());
                    String username = editTextUsername.getText().toString();
                    double balance = Double.parseDouble(editTextBalance.getText().toString());

                    //Account register logic

                    Snackbar.make(buttonRegister, "Account created successfully! Please Login ", Snackbar.LENGTH_LONG).show();

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
        editTextBalance = findViewById(R.id.editTextBalance);
        editTextNumber = findViewById(R.id.editTextNumber);
        textInputLayoutUsername = findViewById(R.id.textInputLayoutUsername);
        textInputLayoutBalance = findViewById(R.id.textInputLayoutBalance);
        textInputLayoutNumber = findViewById(R.id.textInputLayoutNumber);
        buttonRegister = findViewById(R.id.buttonRegister);

    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String number = editTextNumber.getText().toString();
        String Username = editTextUsername.getText().toString();
        String balance = editTextBalance.getText().toString();

        //Handling validation for number field
        if (number.isEmpty()) {
            valid = false;
            textInputLayoutNumber.setError("Please enter valid account number!");
            return valid;
        } else {
            if (number.matches("\\d+")) {
                valid = true;
                textInputLayoutNumber.setError(null);
            } else {
                valid = false;
                textInputLayoutNumber.setError("Account number must be numeric!");
                return valid;
            }

            if (number.length() > 5) {
                valid = true;
                textInputLayoutNumber.setError(null);
            } else {
                valid = false;
                textInputLayoutNumber.setError("Account number is too short!");
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

        //Handling validation for balance field
        if (balance.isEmpty()) {
            valid = false;
            textInputLayoutBalance.setError("Please enter valid balance!");
            return valid;
        } else {
            if (Double.parseDouble(balance) >= 0) {
                valid = true;
                textInputLayoutBalance.setError(null);
            } else {
                valid = false;
                textInputLayoutBalance.setError("Balance must be positive or 0!");
                return valid;
            }

        }


        return valid;
    }
}