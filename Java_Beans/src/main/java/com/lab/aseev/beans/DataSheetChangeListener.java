package com.lab.aseev.beans;

import java.util.EventListener;

public interface DataSheetChangeListener extends EventListener {
    void dataChange(DataSheetChangeEvent e);
}
