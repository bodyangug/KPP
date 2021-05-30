package com.lab.aseev.tcp.second.server;


import com.lab.aseev.tcp.second.interfaces.Result;

import java.io.Serializable;

public class ResultImpl implements Result, Serializable {
    private Object output;
    private double scoreTime;

    public ResultImpl(Object o, double c) {
        this.output = o;
        this.scoreTime = c;
    }

    @Override
    public Object output() {
        return this.output;
    }

    @Override
    public double scoreTime() {
        return this.scoreTime;
    }
}
