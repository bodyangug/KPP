package com.example.parsers.parsers.formulas;

import java.util.ArrayList;
import java.util.List;

public class Repo {
    private final List<Data> hrn = new ArrayList<>();

    public void add(Data peremennye) {
        hrn.add(peremennye);
    }

    public List<Data> getHrn() {
        return hrn;
    }

}
