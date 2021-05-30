package com.lab.aseev.tcp.second.client;


import com.lab.aseev.tcp.second.interfaces.Result;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Client {
    public static void  main(String[] args) {
            try {
                Scanner in = new Scanner(System.in);
                for(int j = 0; j < 10; j++) {
                    Socket client = new Socket("localhost", 6789);
                    for (int i = 10; i > -1; i--) {
                        ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                        String classFile = "C:\\Users\\aseev\\Desktop\\University\\KPP\\KPP\\NET_TCP_SOCKETS\\target\\classes\\com\\lab\\aseev\\tcp\\second\\client\\JobOne.class";
                        out.writeObject(classFile);

                        FileInputStream fis = new FileInputStream(classFile);
                        byte[] b = new byte[fis.available()];
                        fis.read(b);
                        out.writeObject(b);

                        //Формируем объект
                        JobOne aJob = new JobOne(i);
                        out.writeObject(aJob);

                        //результат
                        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                        classFile = (String) ois.readObject();
                        classFile = classFile.replaceFirst("server", "client");
                        b = (byte[]) ois.readObject();

                        if (Files.notExists(Paths.get(classFile))) {
                            FileOutputStream fos = new FileOutputStream(classFile);
                            fos.write(b);
                        }

                        Result r = (Result) ois.readObject();
                        System.out.println("result = " + r.output() + ", time = " + r.scoreTime() + "ns.");
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
    }
}
