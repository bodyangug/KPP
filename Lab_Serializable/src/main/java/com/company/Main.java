package com.company;

import com.company.People.ReaderInfo;
import com.company.People.UserSecretInfo;
import com.company.Service.Methods;
import com.company.biblio.Book;
import com.company.biblio.BookCase;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Methods methods = new Methods();
        String fileForSerialize = Main.class.getClassLoader().getResource("SerializedFiles/save.ser").getPath();
        String fileForExternalize = "SerializedFiles/saveExtern.ser";
        ReaderInfo readerInfo = new ReaderInfo("Ivanov Ivan Ivanovich");

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Make choice:\n" +
                    "1) Serialize\n" +
                    "2) Deserialize\n" +
                    "3) Externalize\n" +
                    "4) Deserialize\n" +
                    "5) Exit");

            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    long time = System.nanoTime();

                    Book book = new Book("War and world");
                    BookCase saved = new BookCase(readerInfo, book);
                    System.out.println(saved);

                    methods.doSerialize(saved, fileForSerialize);
                    System.out.println("time serialize " + (System.nanoTime() - time));
                }
                ;
                case 2: {
                    long time = System.nanoTime();

                    BookCase saved = (BookCase) methods.unSerialize(fileForSerialize);

                    System.out.println("time deserialize " + (double) (System.nanoTime() - time));
                    System.out.println(saved);
                }
                case 3: {
                    long time = System.nanoTime();

                    String somesecret = "some passport data";
                    Book book = new Book("War and world");
                    UserSecretInfo userInfo = new UserSecretInfo(book, somesecret);

                    methods.doSerialize(userInfo, fileForExternalize);
                    System.out.println("Time externalize " + (System.nanoTime() - time));
                }
                case 4: {
                    long time = System.nanoTime();

                    UserSecretInfo userInfo = (UserSecretInfo) methods.unSerialize(fileForExternalize);

                    System.out.println("Time  " + (System.nanoTime() - time));
                    System.out.println(userInfo);
                }
                case 5:
                    return;

                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }

        }


    }

}
