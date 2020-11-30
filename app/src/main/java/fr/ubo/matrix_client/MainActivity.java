package fr.ubo.matrix_client;

import androidx.appcompat.app.AppCompatActivity;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;



import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String SERVER_IP = "172.28.224.229";
    private static final int SERVER_PORT = 3030;
    private static final String clientType = "matrix";
    //MyMatrix matrix = new MyMatrix(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyMatrix(this));
        System.out.println("9bl tray");

        try{
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("after socket");
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            server_connection serverConn = new server_connection(this,socket);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("before start thread");
            new Thread(serverConn).start();
            System.out.println("after starting thread");


        }catch(Exception e){}

    }
}