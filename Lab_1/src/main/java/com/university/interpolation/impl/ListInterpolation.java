package com.university.interpolation.impl;


import com.university.interpolation.Interpolator;
import com.university.point.impl.Point2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ListInterpolation extends Interpolator {

    private List<Point2D> data = null;

    public ListInterpolation(List<Point2D> data) {
        this.data = data;
    }

    public ListInterpolation() {
        data = new ArrayList<Point2D>();
    }

    public ListInterpolation(Point2D[] data) {
        this();
        Collections.addAll(this.data, data);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ListInterpolation fun = new ListInterpolation();
        int num;
        double x;
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("Количество точек: ");
            num = in.nextInt();
        } while (num <= 0);
        for (int i = 0; i < num; i++) {
            x = 1.0 + (5.0 - 1.0) * Math.random();
            fun.addPoint(new Point2D(x, Math.sin(x)));
        }
        System.out.println("Интерполяция по: " + fun.numPoints() + "точкам");
        System.out.println("Несортированый набор: ");
        for (int i = 0; i < fun.numPoints(); i++) {
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));
        }
        fun.sort();
        System.out.println("Отсортированый набор: ");
        for (int i = 0; i < fun.numPoints(); i++) {
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));
        }
        System.out.println("Минимальное значение х : " + fun.getPoint(0).getX());
        System.out.println("Максимальное значение х: " + fun.getPoint(fun.numPoints() - 1).getX());
        x = 0.5 * (fun.getPoint(0).getX() + fun.getPoint(fun.numPoints() - 1).getX());
        System.out.println("Значение интеролляции fun(" + x + ")=" + fun.evalf(x));
        System.out.println("Точное значение sin(" + x + ")=" + Math.sin(x));
        System.out.println("Абсолютная ошибка = " + Math.abs(fun.evalf(x) - Math.sin(x)));
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        data.clear();
    }

    @Override
    public int numPoints() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public void addPoint(Point2D pt) {
        // TODO Auto-generated method stub
        data.add(pt);
    }

    @Override
    public Point2D getPoint(int i) {
        // TODO Auto-generated method stub
        return data.get(i);
    }

    @Override
    public void setPoint(int i, Point2D pt) {
        // TODO Auto-generated method stub
        data.set(i, pt);
    }

    @Override
    public void sort() {
        // TODO Auto-generated method stub
        java.util.Collections.sort(data);
    }

}
