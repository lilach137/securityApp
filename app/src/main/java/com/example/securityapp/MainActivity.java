package com.example.securityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText main_EDT_password;
    private MaterialButton main_BTN_sign_in;
    private static BatteryManager batteryManager;
    private ConnectivityManager connectManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_BTN_sign_in = findViewById(R.id.main_BTN_sign_in);
        main_BTN_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBattery() == 1)
                    if (checkWifi() == 1)
                        if (checkDate() == 1)
                            if (checkLanguage() == 1)
                    Toast.makeText(getApplicationContext(),"Great!!!", Toast.LENGTH_SHORT).show();
            }
        });

    }



    public int checkDate(){
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        main_EDT_password = findViewById(R.id.main_EDT_password);
        String input = main_EDT_password.getText().toString();
        currentDate.replace("-","");

        if (currentDate.equals(input)){
            return 1;
        }
        Toast.makeText(getApplicationContext(),"Please change the date", Toast.LENGTH_SHORT).show();
        return 0;
    }

    public int checkBattery(){
        batteryManager = (BatteryManager) (MainActivity.this).getSystemService(Context.BATTERY_SERVICE);
        if (batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY) > 50)
            return 1;
        else
            Toast.makeText(getApplicationContext(),"You have to much battery :(", Toast.LENGTH_SHORT).show();
        return 0;
    }

    public int checkWifi(){
        connectManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected() == false)
            return 1;
        else
            Toast.makeText(getApplicationContext(),"WIFI is connect :(", Toast.LENGTH_SHORT).show();
        return 0;
    }

    public int checkLanguage(){
        String lang = Locale.getDefault().getDisplayLanguage();
        if (lang.equals("English"))
            return 1;
        else
            Toast.makeText(getApplicationContext(),"Please change language :(", Toast.LENGTH_SHORT).show();
            return 0;

    }


}