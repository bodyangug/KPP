package com.lab.aseev.tcp.second.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args)
    {
            try {
                ServerSocket serverSocket = new ServerSocket(6789,10);
                while(true) {
                    Socket clientsocket = serverSocket.accept();
                    new HandleClient(clientsocket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
