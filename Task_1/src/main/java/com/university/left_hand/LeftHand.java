package com.university.left_hand;


import com.university.Evaluatable;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LeftHand implements Evaluatable {
    private double a;

    public LeftHand(double a) {
        this.a = a;
    }

    public LeftHand() {
        this(0);

    }

    public static void main(String[] args) throws FileNotFoundException {
        LeftHand fun = new LeftHand();
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.println("a: ");
        double a = in.nextDouble();
        fun.setA(a);
        System.out.println("xBeg: ");
        double xBeg = in.nextDouble();
        System.out.println("xEnd: ");
        double xEnd = in.nextDouble();
        System.out.println("xStep: ");
        double xStep = in.nextDouble();
        System.out.println("Параметр a: " + fun.getA());
        PrintWriter out = new PrintWriter("result/LeftHand/LeftHand_A" + a + ".dat");
        for (double x = xBeg; x <= xEnd; x += xStep) {
            System.out.printf("x: %6.4f\tf: %6.4f\n", x, fun.evalf(x));
            out.printf("x: %6.4f\tf: %6.4f\n", x, fun.evalf(x));
        }
        out.close();
    }

    @Override
    public double evalf(double x) {
        // TODO Auto-generated method stub
        return 1.0 / Math.pow(Math.cosh(x), 2) - a * x;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }
}
