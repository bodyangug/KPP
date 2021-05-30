package com.lab.aseev.beans;

public class Data {
    private double x, y;
    private String date;

    public Data(double x, double y, String date) {
        this.x = x;
        this.y = y;
        this.date = date;
    }

    public Data() {
        this(0, 0, "");
    }

    public double getX() {
        return x;
    }

    public void setX(double newX) {
        x = newX;
    }

    public double getY() {
        return y;
    }

    public void setY(double newY) {
        y = newY;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String newDate) {
        date = newDate;
    }
}
