package fr.ubo.matrix_client;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class server_connection extends MyMatrix  implements Runnable{
    private Socket server;
    private BufferedReader in;
    private PrintWriter out;
    //MyMatrix matrix = new MyMatrix();



    public server_connection(Context context,Socket s)throws IOException{
        super(context);
        server = s;
        in = new BufferedReader( new InputStreamReader(server.getInputStream()));
        out = new PrintWriter(server.getOutputStream(),true);
    }

    @Override
    public void run() {





        System.out.println("IoRMatrix SERVEUR Start ...");
        try {
            super.afficher();
            super.init();
            String s ;
            while(!(s = in.readLine()).equals("exit")) {
                if(s.equals("d")) { System.out.println("DROITE"); super.incX(); System.out.print("IoRMatrix_S > "); }
                if(s.equals("g")) { System.out.println("GAUCHE"); super.decX(); System.out.print("IoRMatrix_S > "); }
                if(s.equals("b")) { System.out.println("BAS"); super.incY(); System.out.print("IoRMatrix_S > "); }
                if(s.equals("h")) { System.out.println("HAUT"); super.decY(); System.out.print("IoRMatrix_S > "); }
                if(s.equals("init")) { System.out.println("INIT"); super.init(); System.out.print("IoRMatrix_S > "); }
                if(s.equals("afficher")) { System.out.println("AFFICHER") ;super.afficher();System.out.print("IoRMatrix_S > ");}
                if(s.equals("cacher")) { System.out.println("CACHER") ;super.cacher();System.out.print("IoRMatrix_S > ");}
                //System.out.println("server says: " + s);
            }
            super.cacher();
            System.out.println("Bye");
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
