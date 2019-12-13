package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    static Vector<ClientHalder> clientHalders = new Vector<> ( );
    static int i = 0;

    public static void main( String[] args ) throws IOException {
        ServerSocket serverSocket = new ServerSocket ( 8080 );
        Socket socket;
        while (true) {
            socket = serverSocket.accept ( );
            System.out.println ( "Запрос нового клиента получен: " + socket );
            DataInputStream dataInputStream = new DataInputStream ( socket.getInputStream ( ) );
            DataOutputStream dataOutputStream = new DataOutputStream ( socket.getOutputStream ( ) );
            System.out.println ( "Создание нового обработчика для этого клиента ..." );
            ClientHalder clientHalder = new ClientHalder ( socket , "client" , dataInputStream , dataOutputStream );
            Thread thread = new Thread ( clientHalder );
            System.out.println ( "Добавление этого клиента в список активных клиентов" );
            clientHalders.add ( clientHalder );
            thread.start ( );
            i++;

        }
    }
}
