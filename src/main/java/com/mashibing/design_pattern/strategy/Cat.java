package com.mashibing.design_pattern.strategy;

public class Cat {

    int age;

    int weight;

    public Cat() {
    }

    public Cat(int age, int weight) {
        this.age = age;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "age=" + age +
                ", weight=" + weight +
                '}';
    }
}
