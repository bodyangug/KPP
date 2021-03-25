package com.company.People;

import java.io.Serializable;

public class ReaderInfo implements Serializable{

    private String readerName;

    public ReaderInfo(String readerName) {
        this.readerName = readerName;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    @Override
    public String toString() {
        return "ReaderInfo{" +
                "readerName='" + readerName + '\'' +
                '}';
    }
}


