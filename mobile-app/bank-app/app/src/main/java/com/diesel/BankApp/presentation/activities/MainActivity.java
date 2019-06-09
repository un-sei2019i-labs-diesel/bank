package com.diesel.BankApp.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.diesel.BankApp.R;
import com.diesel.BankApp.businessLogic.controllers.MainController;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Declaration Button
    Button buttonTransaction;

    ListView historyList;

    ArrayList<String> history;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView username = findViewById(R.id.usernameTitle);
        username.setText(
                MainController.usuario.getName());

        final TextView account = findViewById(R.id.accountNumber);
        account.setText(
                String.valueOf(MainController.cuenta.getNumber()));

        final TextView balance = findViewById(R.id.balanceTotal);
        balance.setText(
                String.valueOf(MainController.cuenta.getBalance()));

        history = new ArrayList<>();
        String[] splitter = MainController.cuenta.getHistory().split(";");
        for (int i = 0; i < splitter.length; i++){
            history.add(splitter[i]);
        }
        initViews();
        initLogOutTextView();
        //set click event of transaction button
        buttonTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
                startActivity(intent);
            }
        });


    }


    private void initLogOutTextView() {
        TextView textViewLogOut = findViewById(R.id.textViewLogOut);
        textViewLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        buttonTransaction = findViewById(R.id.buttonTransaction);
        historyList = findViewById(R.id.historyList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, history);
        historyList.setAdapter(adapter);

    }

}
