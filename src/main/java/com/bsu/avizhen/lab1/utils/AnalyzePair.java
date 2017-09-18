package com.bsu.avizhen.lab1.utils;

/**
 * Created by Александр on 17.09.2017.
 */
public class AnalyzePair {
    private int index;
    private int value;

    public AnalyzePair(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
