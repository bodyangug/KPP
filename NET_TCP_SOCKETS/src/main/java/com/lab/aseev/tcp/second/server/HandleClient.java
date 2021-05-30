package com.lab.aseev.tcp.second.server;


import com.lab.aseev.tcp.second.interfaces.Executable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HandleClient implements Runnable {
    Socket socket;

    public HandleClient(Socket s) {
        this.socket = s;
        this.run();
    }

    @Override
    public void run() {
        while (true) {
            try {
                ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());
                String classFile = (String) in.readObject();

                classFile = classFile.replaceFirst("client", "server");
                byte[] b = (byte[]) in.readObject();

                if (Files.notExists(Paths.get(classFile))) {
                    FileOutputStream fos = new FileOutputStream(classFile);
                    fos.write(b);
                }

                //Получаем объект - задание и вычисляем задачу
                Executable executable = (Executable) in.readObject();

                double startTime = System.nanoTime();
                Object output = executable.execute();
                double endTime = System.nanoTime();
                double completionTime = endTime - startTime;
                //Формирование объекта
                ResultImpl r = new ResultImpl(output, completionTime);
                ObjectOutputStream out = new ObjectOutputStream(this.socket.getOutputStream());
                classFile = "C:\\Users\\aseev\\Desktop\\University\\KPP\\KPP\\NET_TCP_SOCKETS\\target\\classes\\com\\lab\\aseev\\tcp\\second\\server\\ResultImpl.class";
                out.writeObject(classFile);

                FileInputStream fis = new FileInputStream(classFile);
                byte[] bo = new byte[fis.available()];
                fis.read(bo);

                out.writeObject(bo);
                out.writeObject(r);
                if (output == null) {
                    socket.close();
                    break;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
