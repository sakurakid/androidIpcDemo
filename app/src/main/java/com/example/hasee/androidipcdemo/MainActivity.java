package com.example.hasee.androidipcdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         UserManger.SUserId = 2;
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);

    }


}
