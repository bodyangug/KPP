package com.lab.aseev.tcp.second.client;


import com.lab.aseev.tcp.second.interfaces.Executable;

import java.io.Serializable;
import java.math.BigInteger;

public class JobOne implements Serializable, Executable {

    private final static long serialVersionUID = 2L;
    private int n;

    public JobOne(int n)
    {
        this.n = n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    @Override
    public String toString() {
        return "N: "+this.n;
    }

    @Override
    public Object execute() {
        BigInteger res = BigInteger.valueOf(1);
        if(!(this.n < 1)) {
            for (int i = 1; i < this.n + 1; i++) {
                res = res.multiply(BigInteger.valueOf(i));
            }
            return res;
        } else {
            return null;
        }
    }
}
