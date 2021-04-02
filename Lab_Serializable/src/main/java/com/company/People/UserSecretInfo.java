package com.company.People;

import com.company.biblio.Book;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Base64;

public class UserSecretInfo implements Externalizable {

    private Book book;
    private String superSecretInformation;

    public UserSecretInfo(Book book, String superSecretInformation) {
        this.book = book;
        this.superSecretInformation = superSecretInformation;
    }

    public UserSecretInfo() {
    }

    public Book getBook() {
        return book;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.getBook());
        out.writeObject(this.encryptString(this.getSuperSecretInformation()));
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        book = (Book) in.readObject();
        superSecretInformation = this.decryptString((String) in.readObject());
    }

    private String encryptString(String data) {
        String encryptedData = Base64.getEncoder().encodeToString(data.getBytes());
        System.out.println(encryptedData);
        return encryptedData;
    }

    private String decryptString(String data) {
        String decrypted = new String(Base64.getDecoder().decode(data));
        System.out.println(decrypted);
        return decrypted;
    }

    public String getSuperSecretInformation() {
        return superSecretInformation;
    }

    @Override
    public String toString() {
        return "UserSecretInfo{" +
                "book=" + book +
                ", superSecretInformation='" + superSecretInformation + '\'' +
                '}';
    }
}