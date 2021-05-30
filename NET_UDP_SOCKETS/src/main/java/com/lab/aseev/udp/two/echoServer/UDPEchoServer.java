package com.lab.aseev.udp.two.echoServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPEchoServer extends UDPServer {

    public final static int DEFAULT_PORT = 7;

    public UDPEchoServer() {
        super(DEFAULT_PORT);
    }

    public static void main(String[] args) {

        UDPServer server = new UDPEchoServer();
        Thread t = new Thread(server);
        t.start();

        System.out.println("Start echo-server...");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        server.shutDown();
        System.out.println("Finish echo-server...");
    }

    @Override
    public void respond(DatagramSocket socket, DatagramPacket request) throws IOException {

        DatagramPacket reply = new DatagramPacket(request.getData(),
                request.getLength(), request.getAddress(), request.getPort());
        socket.send(reply);
    }

}
