package com.example.hasee.androidipcdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketActivity extends AppCompatActivity {
    private Socket mClientSocket;
    private PrintWriter mPrintWrite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);


    }


    private void connectTCPServer() {
        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", 8688);
                mClientSocket = socket;
                mPrintWrite = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

                //handle处理

                //接收服务器的消息
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (!SocketActivity.this.isFinishing()){
                    String msg = br.readLine();
                    System.out.println("receive"+msg);

                    if (msg!=null){
                        //将收到的信息处理
                    }
                }
                System.out.println("quite...");
                //关闭流
                socket.close();


            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

}
