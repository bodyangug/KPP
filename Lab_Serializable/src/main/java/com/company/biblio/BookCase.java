package com.company.biblio;

import com.company.People.ReaderInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BookCase implements Serializable {

    transient ReaderInfo readerInfo;
    private Book book;

    public BookCase(ReaderInfo readerInfo, Book book) {
        this.readerInfo = readerInfo;
        this.book = book;
    }

    public ReaderInfo getReaderInfo() {
        return readerInfo;
    }

    public void setReaderInfo(ReaderInfo readerInfo) {
        this.readerInfo = readerInfo;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookCase{" +
                "readerInfo=" + readerInfo +
                ", book=" + book +
                '}';
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("BookCase writeObject");
        out.defaultWriteObject();
        out.writeObject(readerInfo);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println("BookCase readObject");
        // deserialize the non-transient data members first;
        in.defaultReadObject();
        // Read the readerInfo
        setReaderInfo((ReaderInfo) in.readObject());

    }
}
