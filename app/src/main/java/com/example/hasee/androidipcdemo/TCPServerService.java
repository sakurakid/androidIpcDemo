package com.example.hasee.androidipcdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * socket的练习
 */
public class TCPServerService extends Service {
    private boolean mIsServiceDestroyed = false;
    private String[] mDefinedMessages = new String[]{
      "hi hao are you",
      "what you name",
      "llalalallalal"
    };
    public TCPServerService() {
    }

    @Override
    public void onDestroy() {
        mIsServiceDestroyed = true;
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    private class TcpServer implements Runnable{

        @Override
        public void run() {
            ServerSocket serverSocket = null;

            try {
                //监听本地端口
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("faied 8688");
                e.printStackTrace();
                return;
            }

            while (!mIsServiceDestroyed){
                //接收客户端请求
                try {
                    final Socket client = serverSocket.accept();
                    System.out.println("accept");

                    new Thread(){
                        @Override
                        public void run() {
                            //接收客户端的消息
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void responseClient(Socket socket) throws IOException{
        //接收客户端的信息
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //向客户端发消息
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

        out.println("欢迎来到聊天室");

        while (!mIsServiceDestroyed){
            String str = in.readLine();
            System.out.println("msg from client:"+str);
            if(str == null){
                //客户端断开
                break;
            }
            int i = new Random().nextInt(mDefinedMessages.length);
            String msg = mDefinedMessages[i];
            out.println(msg);
            System.out.println("send:"+msg);
        }
        System.out.println("client quit");

        //关闭流
        socket.close();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw null;
    }
}
