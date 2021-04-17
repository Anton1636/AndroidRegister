package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Kaban", "----Hello my fiend------");
        Log.e("Problem", "----Kill------");
        setContentView(R.layout.activity_main);
    }
}