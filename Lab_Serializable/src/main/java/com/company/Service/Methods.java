package com.company.Service;

import java.io.*;

public class Methods {

    public void doSerialize (Object o, String filename) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(o);
        objectOutputStream.close();
    }

    public Object unSerialize (String filename) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filename);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        return objectInputStream.readObject();
    }

}
