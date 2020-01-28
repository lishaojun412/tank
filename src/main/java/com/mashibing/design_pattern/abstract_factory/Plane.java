package com.mashibing.design_pattern.abstract_factory;

public class Plane implements Movable {
    @Override
    public void go() {
        System.out.println("fly");
    }
}
