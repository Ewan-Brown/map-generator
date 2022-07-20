package com.ewan.landgenerator.display;

import java.awt.*;
//One display for each render possibility (think of TPT's render modes)
public abstract class Display {
    public abstract Color[][] getColorGrid();
}
