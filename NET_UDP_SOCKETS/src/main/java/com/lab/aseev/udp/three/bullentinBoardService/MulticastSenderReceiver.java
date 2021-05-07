package com.lab.aseev.udp.three.bullentinBoardService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class MulticastSenderReceiver {
    private String name;
    private InetAddress address;
    private int port = 3456;
    private MulticastSocket group;

    MulticastSenderReceiver(String name)
    {
        this.name = name;
        System.out.println("name = "+name);
        try{
            address = InetAddress.getByName("224.0.0.1");
            group = new MulticastSocket(port);
            new Receiver().start();
            new Sender().start();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private class Sender extends Thread {

        @Override
        public void run() {

            try{
                BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));

                while(true)
                {
                    String message = name + ": "+fromUser.readLine();
                    byte[] out = message.getBytes();
                    DatagramPacket packet = new DatagramPacket(out, out.length, address, port);
                    group.send(packet);
                }

            } catch (Exception e){}
        }
    }

    private class Receiver extends Thread{
        @Override
        public void run() {
            try {
                byte[] in = new byte[256];
                DatagramPacket packet = new DatagramPacket(in, in.length);
                group.joinGroup(address);

                while(true)
                {
                    group.receive(packet);
                    System.out.println(new String(packet.getData(), 0,packet.getLength()));
                }

            } catch (Exception e){}
        }
    }


    public static void main(String[] args) {
      // new MulticastSenderReceiver(args[0]);
      // new MulticastSenderReceiver(new Scanner(System.in).next());

       Scanner sc = new Scanner(System.in);
       String name = sc.nextLine();

       new MulticastSenderReceiver(name);
    }


}
