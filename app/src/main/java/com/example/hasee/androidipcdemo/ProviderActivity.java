package com.example.hasee.androidipcdemo;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProviderActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        Uri uri = Uri.parse("content://com.example.hasee.androidipcdemo.ptovider");
        getContentResolver().query(uri,null,null,null);
        getContentResolver().query(uri,null,null,null);
        getContentResolver().query(uri,null,null,null);
    }
}
