package com.example.one2nineapp.game;

public class Number {
    private int value;
    private String label;

    public Number(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
}
