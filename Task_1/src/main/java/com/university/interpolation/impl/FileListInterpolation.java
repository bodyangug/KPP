package com.university.interpolation.impl;

import com.university.point.impl.Point2D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileListInterpolation extends ListInterpolation {

    public FileListInterpolation() {
        super();
    }

    public static void main(String[] args) {
        FileListInterpolation fun = new FileListInterpolation();
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
        System.out.println("Минимальное значение х: " + fun.getPoint(0).getX());
        System.out.println("Максимальное значение х: " + fun.getPoint(fun.numPoints() - 1).getX());
        System.out.println("Сохраняем в файл");

        try {
            fun.writeToFile("result/FileListInterpolation/data.dat");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Считываем из файла");
        try {
            fun.readFromFile("result/FileListInterpolation/data.dat");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Данные из файла: ");
        fun.sort();
        for (int i = 0; i < fun.numPoints(); i++) {
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));
        }
        System.out.println("Минимальное значение х:" + fun.getPoint(0).getX());
        System.out.println("Максимальное значение х:" + fun.getPoint(fun.numPoints() - 1).getX());
        x = 0.5 * (fun.getPoint(0).getX() + fun.getPoint(fun.numPoints() - 1).getX());
        System.out.println("Значение интерполяции fun(" + x + ")=" + fun.evalf(x));
        System.out.println("Точное значение sin(" + x + ")=" + Math.sin(x));
        System.out.println("Абсолютная ошибка = " + Math.abs(fun.evalf(x) - Math.sin(x)));
        fun.clear();
        for (x = 1.0; x < 7.0; x += 0.1) {
            fun.addPoint(new Point2D(x, Math.sin(x)));
        }
        try {
            fun.writeToFile("result/FileListInterpolation/TblFnc.dat");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void readFromFile(String fileName) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String s = in.readLine();
        clear();
        while ((s = in.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(s);
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            addPoint(new Point2D(x, y));
        }
        in.close();
    }

    public void writeToFile(String fileName) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));
        out.printf("%9s%25s\n", "x", "y");
        for (int i = 0; i < numPoints(); i++) {
            out.println(getPoint(i).getX() + "\t" + getPoint(i).getY());
        }
        out.close();
    }
}