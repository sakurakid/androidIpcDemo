package com.example.hasee.androidipcdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         UserManger.SUserId = 2;
        Intent intent = new Intent(this,ProviderActivity.class);
        startActivity(intent);

    }
    //写入序列化到文件
    private void persistToFile(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User(1,"hhhhh",false);
                File dir = new File("D:\\chche");
                if (!dir.exists()){
                    dir.mkdirs();
                }

                File cachedFile = new File("D:");
                ObjectOutputStream objectOutputStream = null;
                try {
                    objectOutputStream = new ObjectOutputStream(new FileOutputStream(cachedFile));
                    //写入序列化
                    objectOutputStream.writeObject(user);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {

                }
            }
        }).start();
    }


}
