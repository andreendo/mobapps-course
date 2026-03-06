package com.example.imcapp;

import java.io.Serializable;

public class PersonData implements Serializable {
    String name;
    int age;
    float height, weight;

    @Override
    public String toString() {
        return "PersonData{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }

    public PersonData(String name, int age, float height, float weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public float getIMC() {
        return weight / (height * height);
    }
}
