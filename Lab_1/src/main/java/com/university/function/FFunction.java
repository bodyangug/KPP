package com.university.function;


import com.university.Evaluatable;

public class FFunction implements Evaluatable {
    private double a;

    public FFunction(Double a) {
        this.a = a;
    }

    @Override
    public double evalf(double x) {
        // TODO Auto-generated method stub
        return Math.exp(x * x) * Math.sin(x);
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public static void main(String[] args) {
        System.out.println("FFunction check");

        FFunction fun = new FFunction(0.0);

        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.println("xBeg: ");
        double xBeg = in.nextDouble();
        System.out.println("xEnd: ");
        double xEnd = in.nextDouble();
        System.out.println("xStep: ");
        double xStep = in.nextDouble();

        System.out.println("Param a: " + fun.getA());
        for (double x = xBeg; x <= xEnd; x += xStep) {
            System.out.println(x + " " + fun.evalf(x));

        }
        System.out.print("x: ");
        double x = in.nextDouble();
        System.out.println("aBeg: ");
        double aBeg = in.nextDouble();
        System.out.println("aEnd: ");
        double aEnd = in.nextDouble();
        System.out.println("aStep: ");
        double aStep = in.nextDouble();

        System.out.println("Variable x: " + x);
        for (double a = aBeg; a <= aEnd; a += aStep) {
            System.out.println(fun.getA() + " " + fun.evalf(x));

        }

    }
}
