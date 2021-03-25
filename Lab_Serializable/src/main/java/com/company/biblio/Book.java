package com.company.biblio;

import java.io.Serializable;

public class Book implements Serializable {

    private String info;

    public Book(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Book{" +
                "info='" + info + '\'' +
                '}';
    }
}
