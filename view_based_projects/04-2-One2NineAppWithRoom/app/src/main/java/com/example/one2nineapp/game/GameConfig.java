package com.example.one2nineapp.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class GameConfig {
    private List<List<Number>> configurations;
    private int current;

    public GameConfig() {
        configurations = new ArrayList<>();
        configurations.add(getNumberNumber());
//        configurations.add(getNumberRoman());
//        configurations.add(getNumberEnglish());

        current = 0;
    }

    private List<Number> getNumberNumber() {
        return Arrays.asList(
                new Number(1, "1"),
                new Number(2, "2"),
                new Number(3, "3"),
                new Number(4, "4"),
                new Number(5, "5"),
                new Number(6, "6"),
                new Number(7, "7"),
                new Number(8, "8"),
                new Number(9, "9")
        );
    }

    private List<Number> getNumberRoman() {
        return Arrays.asList(
                new Number(1, "I"),
                new Number(2, "II"),
                new Number(3, "III"),
                new Number(4, "IV"),
                new Number(5, "V"),
                new Number(6, "VI"),
                new Number(7, "VII"),
                new Number(8, "VIII"),
                new Number(9, "IX")
        );
    }

    private List<Number> getNumberEnglish() {
        return Arrays.asList(
                new Number(1, "one"),
                new Number(2, "two"),
                new Number(3, "three"),
                new Number(4, "four"),
                new Number(5, "five"),
                new Number(6, "six"),
                new Number(7, "seven"),
                new Number(8, "eight"),
                new Number(9, "nine")
        );
    }

    public List<Number> getNextConfiguration() {
        List<Number> ret = configurations.get(current);
        Collections.shuffle(ret);    //shuffle a list
        current++;
        return ret;
    }

    public boolean hasNext() {
        return current < configurations.size();
    }
}
