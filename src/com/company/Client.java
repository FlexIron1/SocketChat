package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final int serverPort = 8080;

    public static void main( String[] args ) throws IOException {
        Scanner scanner = new Scanner ( System.in );
        String address = "localhost";
        Socket socket = new Socket ( address , serverPort );
        DataInputStream dataInputStream = new DataInputStream ( socket.getInputStream ( ) );
        DataOutputStream dataOutputStream = new DataOutputStream ( socket.getOutputStream ( ) );
        Thread sendMessages = new Thread ( new Runnable ( ) {
            @Override
            public void run() {
                while (true) {
                    String messages = scanner.nextLine ( );
                    try {
                        dataOutputStream.writeUTF ( messages );
                    }
                    catch ( IOException e ) {
                        e.printStackTrace ( );
                    }
                }
            }
        } );
        Thread readMessage = new Thread ( new Runnable ( ) {
            @Override
            public void run() {
                while (true) {
                    try {
                        String messages = dataInputStream.readUTF ( );
                        System.out.println ( messages );
                    }
                    catch ( IOException e ) {
                        e.printStackTrace ( );
                    }

                }
            }
        } );
sendMessages.start ();
readMessage.start ();

    }
}
