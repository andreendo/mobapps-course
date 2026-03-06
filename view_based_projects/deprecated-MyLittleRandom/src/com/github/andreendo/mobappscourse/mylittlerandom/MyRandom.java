package com.github.andreendo.mobappscourse.mylittlerandom;

import java.util.Random;

public class MyRandom {
    int begin, end;
    private Random random = new Random();

    public MyRandom(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    public int getRandomNumber() {
        //generate random number
        int myRandom = random.nextInt(Integer.MAX_VALUE) % (end - begin + 1);
        myRandom = begin + myRandom;
        return myRandom;
    }
}
