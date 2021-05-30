package com.university.interpolation.impl;

import com.university.point.impl.Point2D;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class TreeMapInterpolation extends FileListInterpolation {

    private TreeMap<Integer, Point2D> data = null;

    public TreeMapInterpolation(TreeMap<Integer, Point2D> data) {
        this.data = data;
    }

    public TreeMapInterpolation() {
        data = new TreeMap<>();
    }

    public TreeMapInterpolation(Point2D[] data) {
        this();
        Arrays.stream(data).forEach(el -> this.data.put(el.hashCode(), el));
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
        data.put(pt.hashCode(), pt);
    }

    @Override
    public Point2D getPoint(int i) {
        // TODO Auto-generated method stub
        return data.get(i);
    }

    @Override
    public void setPoint(int i, Point2D pt) {
        // TODO Auto-generated method stub
        data.values().toArray()[i] = pt;
    }

    @Override
    public void sort() {
        // TODO Auto-generated method stub
        Point2D[] objects = (Point2D[]) data.values().toArray();
        Arrays.sort(objects);
    }

}
