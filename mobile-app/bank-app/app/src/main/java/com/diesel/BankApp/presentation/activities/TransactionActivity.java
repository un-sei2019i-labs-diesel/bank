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

public class TransactionActivity extends AppCompatActivity {

    //Declaration EditTexts
    EditText editTextAccount;
    EditText editTextAmount;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutAccount;
    TextInputLayout textInputLayoutAmount;

    //Declaration Button
    Button buttonContinue;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        initTextViewGoBack();
        initViews();
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    int account = Integer.parseInt(editTextAccount.getText().toString());
                    double amount = Double.parseDouble(editTextAmount.getText().toString());

                    //transaction logic

                    Snackbar.make(buttonContinue, "Transaction done successfully!", Snackbar.LENGTH_LONG).show();

                }
            }
        });
    }


    //this method used to set transaction TextView click event
    private void initTextViewGoBack() {
        TextView textViewGoBack = findViewById(R.id.textViewGoBack);
        textViewGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextAccount = findViewById(R.id.editTextAccount);
        editTextAmount = findViewById(R.id.editTextAmount);
        textInputLayoutAccount = findViewById(R.id.textInputLayoutAccount);
        textInputLayoutAmount = findViewById(R.id.textInputLayoutAmount);
        buttonContinue = findViewById(R.id.buttonContinue);

    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String account = editTextAccount.getText().toString();
        String amount = editTextAmount.getText().toString();


        //Handling validation for account field
        if (account.isEmpty()) {
            valid = false;
            textInputLayoutAccount.setError("Please enter valid account number!");
            return valid;
        } else {
            if (account.matches("\\d+")) {
                valid = true;
                textInputLayoutAccount.setError(null);
            } else {
                valid = false;
                textInputLayoutAccount.setError("Account number must be numeric!");
                return valid;
            }

            if (account.length() > 5) {
                valid = true;
                textInputLayoutAccount.setError(null);
            } else {
                valid = false;
                textInputLayoutAccount.setError("Account number is too short!");
                return valid;
            }

        }


        //Handling validation for amount field
        if (amount.isEmpty()) {
            valid = false;
            textInputLayoutAmount.setError("Please enter valid amount!");
            return valid;
        } else {
            if (Double.parseDouble(amount) > 0) {
                valid = true;
                textInputLayoutAmount.setError(null);
            } else {
                valid = false;
                textInputLayoutAmount.setError("Amount must be positive!");
                return valid;
            }

        }


        return valid;
    }
}
