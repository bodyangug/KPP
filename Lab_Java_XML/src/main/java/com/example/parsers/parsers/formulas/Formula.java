package com.example.parsers.parsers.formulas;

import java.util.List;

public class Formula {

    public static double square(double per) {
        per = per * per;
        return per;
    }


    public static void findKoef(Repo hr) {
        List<Data> hrn = hr.getHrn();
        double summaxy = 0;
        double summax = 0;
        double summay = 0;
        double sqx = 0;
        double summasqx = 0;
        double k = 0;
        double b = 0;
        for (int i = 0; i < hrn.size(); i++) {
            double x = hrn.get(i).getX();
            double y = hrn.get(i).getY();
            summax = summax + x;
            summay = summay + y;
            summaxy = summaxy + (x * y);
            sqx = square(x);
            summasqx = summasqx + sqx;
            k = ((hrn.size() + 1) * (summaxy - (summax * summay)) / ((hrn.size() + 1) * summasqx) - square(summax));
            b = y - k * x;
        }
        System.out.println("your k ------------ " + k);
        System.out.println("your b ------------ " + b);

    }


}