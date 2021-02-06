package com.example.please_drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }
    public void clickBtn(View view){
        Intent intent = new Intent(packageContext;this,SecondActivity.class);
        startActivity(intent);
    }
}